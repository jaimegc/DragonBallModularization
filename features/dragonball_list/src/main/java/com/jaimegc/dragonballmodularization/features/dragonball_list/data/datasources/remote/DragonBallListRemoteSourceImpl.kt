package com.jaimegc.dragonballmodularization.features.dragonball_list.data.datasources.remote

import com.jaimegc.dragonballmodularization.libraries.base.data.DragonBallInfo
import java.util.*

class DragonBallListRemoteSourceImpl(
    private val dragonBallApi: DragonBallListApi
) : DragonBallListRemoteSource {
    override suspend fun getDragonBall(): List<DragonBallInfo> =
        dragonBallApi.getDragonBall().results.filter {
            it.title.toLowerCase(Locale.ROOT).contains("dragon ball") &&
                it.title.toLowerCase(Locale.ROOT).contains("4-d").not()
        }.sortedByDescending {
            it.startDate
        }
}