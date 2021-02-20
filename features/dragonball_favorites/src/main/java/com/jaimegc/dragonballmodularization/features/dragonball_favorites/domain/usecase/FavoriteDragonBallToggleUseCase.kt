package com.jaimegc.dragonballmodularization.features.dragonball_favorites.domain.usecase

import com.jaimegc.dragonballmodularization.features.dragonball_favorites.domain.repository.FavoriteDragonBallRepository
import com.jaimegc.dragonballmodularization.libraries.base.data.Resource
import com.jaimegc.dragonballmodularization.libraries.base.domain.FlowUseCase
import kotlinx.coroutines.flow.Flow

class FavoriteDragonBallToggleUseCase(
    private val repository: FavoriteDragonBallRepository
) : FlowUseCase<Long, Boolean>() {
    override suspend fun execute(parameters: Long?): Flow<Resource<Boolean>> =
        repository.toggleFavoriteDragonBall(parameters ?: -1L)
}
