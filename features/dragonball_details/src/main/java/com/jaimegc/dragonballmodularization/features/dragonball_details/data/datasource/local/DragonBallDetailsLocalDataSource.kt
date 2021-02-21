package com.jaimegc.dragonballmodularization.features.dragonball_details.data.datasource.local

import com.jaimegc.dragonballmodularization.libraries.local.entity.DragonBallInfoEntity

interface DragonBallDetailsLocalDataSource {
    suspend fun getDragonBallDetails(dragonBallId: Long): DragonBallInfoEntity?
    suspend fun toggleFavoriteDragonBallFav(dragonBallId: Long): Boolean
}
