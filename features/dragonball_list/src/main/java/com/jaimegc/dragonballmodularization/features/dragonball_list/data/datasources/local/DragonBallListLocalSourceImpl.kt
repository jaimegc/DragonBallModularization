package com.jaimegc.dragonballmodularization.features.dragonball_list.data.datasources.local

import com.jaimegc.dragonballmodularization.libraries.base.data.DragonBallInfo
import com.jaimegc.dragonballmodularization.libraries.local.dao.DragonBallDao
import com.jaimegc.dragonballmodularization.libraries.local.entity.DragonBallInfoEntity

class DragonBallListLocalSourceImpl(
    private val dragonBallDao: DragonBallDao
) : DragonBallListLocalSource {
    override suspend fun getDragonBall(): List<DragonBallInfoEntity> =
        dragonBallDao.getAll()

    override suspend fun saveDragonBallList(list: List<DragonBallInfo>) =
        dragonBallDao.insertAll(list.map { it.toEntity() })
}

private fun DragonBallInfo.toEntity(): DragonBallInfoEntity =
    DragonBallInfoEntity(
        id = id,
        data = this
    )