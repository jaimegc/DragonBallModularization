package com.jaimegc.dragonballmodularization.features.dragonball_list.data

import com.jaimegc.dragonballmodularization.features.dragonball_list.data.datasources.local.DragonBallListLocalSource
import com.jaimegc.dragonballmodularization.features.dragonball_list.data.datasources.remote.DragonBallListRemoteSource
import com.jaimegc.dragonballmodularization.features.dragonball_list.domain.repository.DragonBallListRepository
import com.jaimegc.dragonballmodularization.libraries.base.data.DragonBallInfo
import com.jaimegc.dragonballmodularization.libraries.base.data.Resource
import com.jaimegc.dragonballmodularization.libraries.base.util.SchedulerProvider
import com.jaimegc.dragonballmodularization.libraries.local.entity.DragonBallInfoEntity
import com.jaimegc.dragonballmodularization.libraries.remote.NetworkBoundResource
import kotlinx.coroutines.flow.Flow

class DragonBallListRepositoryImpl(
    private val remoteSource: DragonBallListRemoteSource,
    private val localSource: DragonBallListLocalSource,
    private val schedulerProvider: SchedulerProvider
) : DragonBallListRepository {
    override suspend fun getDragonBall(): Flow<Resource<List<DragonBallInfo>>> =
        object : NetworkBoundResource<List<DragonBallInfo>>(schedulerProvider) {
            override suspend fun remoteFetch(): List<DragonBallInfo> =
                remoteSource.getDragonBall()

            override suspend fun saveFetchResult(data: List<DragonBallInfo>) {
                localSource.saveDragonBallList(data)
            }

            override suspend fun localFetch(): List<DragonBallInfo> =
                localSource.getDragonBall().map { it.toDomain(it.isFav) }

            override fun shouldFetchWithLocalData() = true
        }.asFlow()
}

private fun DragonBallInfoEntity.toDomain(isFav: Boolean): DragonBallInfo =
    this.data.apply { this.isFav = isFav }
