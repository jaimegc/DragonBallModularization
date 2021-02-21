package com.jaimegc.dragonballmodularization.features.dragonball_details.domain.usecase

import com.jaimegc.dragonballmodularization.features.dragonball_details.domain.repository.DragonBallDetailsRepository
import com.jaimegc.dragonballmodularization.libraries.base.data.DragonBallInfo
import com.jaimegc.dragonballmodularization.libraries.base.data.Resource
import com.jaimegc.dragonballmodularization.libraries.base.domain.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class DragonBallDetailsUseCase(
    private val repository: DragonBallDetailsRepository
) : FlowUseCase<Long, DragonBallInfo>() {

    override suspend fun execute(parameters: Long?): Flow<Resource<DragonBallInfo>> {
        return repository.getDragonBallDetails(parameters ?: -1L)
            .map { result ->
                if (result is Resource.Success<*>) {
                    val successResult = (result as Resource.Success)
                    successResult.data?.let {
                        return@let Resource.Success(it, successResult.source)
                    } ?: Resource.None
                } else
                    result as Resource.Failure
            }
    }
}