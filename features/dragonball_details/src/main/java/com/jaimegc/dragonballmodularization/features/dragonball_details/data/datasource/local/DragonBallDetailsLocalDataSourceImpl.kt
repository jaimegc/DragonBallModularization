package com.jaimegc.dragonballmodularization.features.dragonball_details.data.datasource.local

import com.jaimegc.dragonballmodularization.libraries.local.dao.DragonBallDao
import com.jaimegc.dragonballmodularization.libraries.local.dao.FavoritesDao
import com.jaimegc.dragonballmodularization.libraries.local.entity.DragonBallInfoEntity

class DragonBallDetailsLocalDataSourceImpl(
    private val dragonBallDao: DragonBallDao,
    private val favoritesDao: FavoritesDao
) : DragonBallDetailsLocalDataSource {
    override suspend fun getDragonBallDetails(dragonBallId: Long): DragonBallInfoEntity? =
        dragonBallDao.getDragonBall(dragonBallId)

    override suspend fun toggleFavoriteDragonBallFav(dragonBallId: Long): Boolean =
        favoritesDao.toggleDragonBallFavState(dragonBallId)
}
