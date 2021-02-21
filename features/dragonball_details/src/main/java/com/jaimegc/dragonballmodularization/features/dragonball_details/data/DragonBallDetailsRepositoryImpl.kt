package com.jaimegc.dragonballmodularization.features.dragonball_details.data

import com.jaimegc.dragonballmodularization.features.dragonball_details.data.datasource.local.DragonBallDetailsLocalDataSource
import com.jaimegc.dragonballmodularization.features.dragonball_details.domain.repository.DragonBallDetailsRepository
import com.jaimegc.dragonballmodularization.libraries.base.data.DragonBallInfo
import com.jaimegc.dragonballmodularization.libraries.base.data.Resource
import com.jaimegc.dragonballmodularization.libraries.base.util.SchedulerProvider
import com.jaimegc.dragonballmodularization.libraries.local.entity.DragonBallInfoEntity
import com.jaimegc.dragonballmodularization.libraries.remote.NetworkBoundResource
import kotlinx.coroutines.flow.Flow

class DragonBallDetailsRepositoryImpl(
    private val localDataSource: DragonBallDetailsLocalDataSource,
    private val schedulerProvider: SchedulerProvider
) : DragonBallDetailsRepository {
    override suspend fun getDragonBallDetails(dragonBallId: Long): Flow<Resource<DragonBallInfo?>> =
        object : NetworkBoundResource<DragonBallInfo?>(schedulerProvider) {
            override suspend fun remoteFetch(): DragonBallInfo? = null

            override suspend fun saveFetchResult(data: DragonBallInfo?) { }

            override suspend fun localFetch(): DragonBallInfo? =
                localDataSource.getDragonBallDetails(dragonBallId)?.let {
                    it.toDomain(it.isFav)
                }

            override fun shouldFetch() = false

        }.asFlow()

    override suspend fun toggleFavoriteDragonBall(dragonBallId: Long): Flow<Resource<Boolean>> =
        object : NetworkBoundResource<Boolean>(schedulerProvider) {
            override suspend fun remoteFetch(): Boolean = false

            override suspend fun saveFetchResult(data: Boolean) { }

            override suspend fun localFetch(): Boolean =
                localDataSource.toggleFavoriteDragonBallFav(dragonBallId)

            override fun shouldFetch() = false
        }.asFlow()
}

private fun DragonBallInfoEntity.toDomain(isFav: Boolean): DragonBallInfo =
    this.data.apply { this.isFav = isFav }
