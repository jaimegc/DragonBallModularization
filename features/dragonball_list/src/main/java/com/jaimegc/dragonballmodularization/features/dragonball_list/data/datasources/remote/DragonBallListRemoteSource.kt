package com.jaimegc.dragonballmodularization.features.dragonball_list.data.datasources.remote

import com.jaimegc.dragonballmodularization.libraries.base.data.DragonBallInfo

interface DragonBallListRemoteSource {
    suspend fun getDragonBall(): List<DragonBallInfo>
}
