package com.jaimegc.dragonballmodularization.features.dragonball_list.data.datasources.remote

import com.jaimegc.dragonballmodularization.libraries.base.data.DragonBallInfo

class DragonBallListRemoteSourceImpl(
    private val dragonBallApi: DragonBallListApi
) : DragonBallListRemoteSource {
    override suspend fun getDragonBall(): List<DragonBallInfo> =
        dragonBallApi.getDragonBall().results
}
