package com.jaimegc.dragonballmodularization.features.dragonball_list.data.datasources.local

import com.jaimegc.dragonballmodularization.libraries.base.data.DragonBallInfo
import com.jaimegc.dragonballmodularization.libraries.local.entity.DragonBallInfoEntity

interface DragonBallListLocalSource {
    suspend fun getDragonBall(): List<DragonBallInfoEntity>
    suspend fun saveDragonBallList(list: List<DragonBallInfo>)
}
