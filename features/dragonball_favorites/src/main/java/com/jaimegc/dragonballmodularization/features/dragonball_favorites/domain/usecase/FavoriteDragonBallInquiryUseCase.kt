package com.jaimegc.dragonballmodularization.features.dragonball_favorites.domain.usecase

import com.jaimegc.dragonballmodularization.features.dragonball_favorites.domain.repository.FavoriteDragonBallRepository
import com.jaimegc.dragonballmodularization.libraries.base.data.DataSource
import com.jaimegc.dragonballmodularization.libraries.base.data.DragonBallInfo
import com.jaimegc.dragonballmodularization.libraries.base.data.Resource
import com.jaimegc.dragonballmodularization.libraries.base.domain.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteDragonBallInquiryUseCase(
    private val repository: FavoriteDragonBallRepository
) : FlowUseCase<Nothing?, List<DragonBallInfo>>() {
    override suspend fun execute(parameters: Nothing?): Flow<Resource<List<DragonBallInfo>>> =
        repository.getFavoriteDragonBall().map {
            Resource.Success(it, DataSource.CACHE)
        }
}