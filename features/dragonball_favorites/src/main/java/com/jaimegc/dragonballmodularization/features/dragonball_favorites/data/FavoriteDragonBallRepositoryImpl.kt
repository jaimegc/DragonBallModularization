package com.jaimegc.dragonballmodularization.features.dragonball_favorites.data

import com.jaimegc.dragonballmodularization.features.dragonball_favorites.data.datasource.local.FavoritesDragonBallLocalDataSource
import com.jaimegc.dragonballmodularization.features.dragonball_favorites.domain.repository.FavoriteDragonBallRepository
import com.jaimegc.dragonballmodularization.libraries.base.data.DragonBallInfo
import com.jaimegc.dragonballmodularization.libraries.base.util.SchedulerProvider
import com.jaimegc.dragonballmodularization.libraries.local.entity.DragonBallInfoEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteDragonBallRepositoryImpl(
    private val localDataSource: FavoritesDragonBallLocalDataSource
) : FavoriteDragonBallRepository {
    override suspend fun getFavoriteDragonBall(): Flow<List<DragonBallInfo>> =
        localDataSource.getFavoriteDragonBall().map { dragonBallList ->
            dragonBallList.map { it.toDomain(it.isFav) }
        }
}

private fun DragonBallInfoEntity.toDomain(isFav: Boolean): DragonBallInfo =
    this.data.apply { this.isFav = isFav }