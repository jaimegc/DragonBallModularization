package com.jaimegc.dragonballmodularization.features.dragonball_favorites.data.datasource.local

import com.jaimegc.dragonballmodularization.libraries.local.dao.FavoritesDao
import com.jaimegc.dragonballmodularization.libraries.local.entity.DragonBallInfoEntity
import kotlinx.coroutines.flow.Flow

class FavoritesDragonBallLocalDataSourceImpl(
    private val favoritesDao: FavoritesDao
) : FavoritesDragonBallLocalDataSource {
    override suspend fun getFavoriteDragonBall(): Flow<List<DragonBallInfoEntity>> =
        favoritesDao.getAllFavorites()
}