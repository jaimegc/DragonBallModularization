package com.jaimegc.dragonballmodularize.base.model

import com.jaimegc.dragonballmodularize.base.domain.FailureData
import com.jaimegc.dragonballmodularize.base.domain.Resource
import com.jaimegc.dragonballmodularize.base.util.NetworkCodes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

/**
 * Executes business logic in its execute method and keep posting updates to the result as
 * [Result<R>].
 */
abstract class FlowUseCase<in P, R>() {
    suspend operator fun invoke(parameters: P? = null): Flow<Resource<R>> = execute(parameters)
        .catch { e ->
            emit(
                Resource.Failure(
                    FailureData(
                        code = NetworkCodes.GENERIC_ERROR,
                        message = e.localizedMessage
                    )
                )
            )
        }

    protected abstract suspend fun execute(parameters: P? = null): Flow<Resource<R>>
}
