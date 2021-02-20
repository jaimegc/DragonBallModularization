package com.jaimegc.dragonballmodularization.features.dragonball_favorites.data.datasource.local

import com.jaimegc.dragonballmodularization.libraries.local.entity.DragonBallInfoEntity
import kotlinx.coroutines.flow.Flow

interface FavoritesDragonBallLocalDataSource {
    suspend fun getFavoriteDragonBall(): Flow<List<DragonBallInfoEntity>>
    suspend fun toggleFavoriteDragonBallFav(dragonBallId: Long): Boolean
}
