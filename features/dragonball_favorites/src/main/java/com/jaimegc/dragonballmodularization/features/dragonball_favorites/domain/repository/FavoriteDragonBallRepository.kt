package com.jaimegc.dragonballmodularization.features.dragonball_favorites.domain.repository

import com.jaimegc.dragonballmodularization.libraries.base.data.DragonBallInfo
import com.jaimegc.dragonballmodularization.libraries.base.data.Resource
import kotlinx.coroutines.flow.Flow

interface FavoriteDragonBallRepository {
    suspend fun getFavoriteDragonBall(): Flow<List<DragonBallInfo>>
    suspend fun toggleFavoriteDragonBall(dragonBallId: Long): Flow<Resource<Boolean>>
}
