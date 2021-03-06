package com.jaimegc.dragonballmodularization.libraries.remote

import com.jaimegc.dragonballmodularization.libraries.remote.util.ResultWrapper
import com.jaimegc.dragonballmodularization.libraries.remote.util.safeApiCall
import com.jaimegc.dragonballmodularization.libraries.base.data.DataSource
import com.jaimegc.dragonballmodularization.libraries.base.data.FailureData
import com.jaimegc.dragonballmodularization.libraries.base.data.Resource
import com.jaimegc.dragonballmodularization.libraries.base.util.SchedulerProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class NetworkBoundResource<T>(private val schedulerProvider: SchedulerProvider) {

    fun asFlow(): Flow<Resource<T>> = flow {
        // check if should fetch data from remote or not
        if (shouldFetch()) {
            if (shouldFetchWithLocalData()) { // should emit cached date with loading state or not
                emit(Resource.Success(data = localFetch(), DataSource.CACHE))
            }

            val remoteResponse = safeApiCall(dispatcher = schedulerProvider.io()) {
                remoteFetch() // fetch the remote source provided
            }

            when (remoteResponse) {
                is ResultWrapper.Success -> {
                    remoteResponse.value?.let {
                        saveFetchResult(remoteResponse.value)
                    }
                    emit(Resource.Success(data = remoteResponse.value, DataSource.REMOTE))
                }

                is ResultWrapper.GenericError -> {
                    emit(
                        Resource.Failure(
                            failureData = FailureData(remoteResponse.code, remoteResponse.message)
                        )
                    )
                }
            }
        } else {
            // get from cache
            emit(Resource.Success(data = localFetch(), DataSource.CACHE))
        }
    }

    abstract suspend fun remoteFetch(): T
    abstract suspend fun saveFetchResult(data: T)
    abstract suspend fun localFetch(): T
    open fun onFetchFailed(throwable: Throwable) = Unit
    open fun shouldFetch() = true
    open fun shouldFetchWithLocalData() = false
}
