package com.jaimegc.dragonballmodularization.features.dragonball_favorites.data

import com.jaimegc.dragonballmodularization.features.dragonball_favorites.data.datasource.local.FavoritesDragonBallLocalDataSource
import com.jaimegc.dragonballmodularization.features.dragonball_favorites.domain.repository.FavoriteDragonBallRepository
import com.jaimegc.dragonballmodularization.libraries.base.data.DragonBallInfo
import com.jaimegc.dragonballmodularization.libraries.base.data.Resource
import com.jaimegc.dragonballmodularization.libraries.base.util.SchedulerProvider
import com.jaimegc.dragonballmodularization.libraries.local.entity.DragonBallInfoEntity
import com.jaimegc.dragonballmodularization.libraries.remote.NetworkBoundResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteDragonBallRepositoryImpl(
    private val localDataSource: FavoritesDragonBallLocalDataSource,
    private val schedulerProvider: SchedulerProvider
) : FavoriteDragonBallRepository {
    override suspend fun getFavoriteDragonBall(): Flow<List<DragonBallInfo>> =
        localDataSource.getFavoriteDragonBall().map { dragonBallList ->
            dragonBallList.map { it.toDomain(it.isFav) }
        }

    override suspend fun toggleFavoriteDragonBall(dragonBallId: Long): Flow<Resource<Boolean>> {
        return object : NetworkBoundResource<Boolean>(schedulerProvider) {
            override suspend fun remoteFetch(): Boolean {
                return false
            }

            override suspend fun saveFetchResult(data: Boolean) { }

            override suspend fun localFetch(): Boolean =
                localDataSource.toggleFavoriteDragonBallFav(dragonBallId)

            override fun shouldFetch() = false
        }.asFlow()
    }
}

private fun DragonBallInfoEntity.toDomain(isFav: Boolean): DragonBallInfo =
    this.data.apply { this.isFav = isFav }