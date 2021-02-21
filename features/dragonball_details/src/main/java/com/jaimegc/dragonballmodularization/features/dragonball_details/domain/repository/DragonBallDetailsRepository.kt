package com.jaimegc.dragonballmodularization.features.dragonball_details.domain.repository

import com.jaimegc.dragonballmodularization.libraries.base.data.DragonBallInfo
import com.jaimegc.dragonballmodularization.libraries.base.data.Resource
import kotlinx.coroutines.flow.Flow

interface DragonBallDetailsRepository {
    suspend fun getDragonBallDetails(dragonBallId: Long): Flow<Resource<DragonBallInfo?>>
    suspend fun toggleFavoriteDragonBall(dragonBallId: Long): Flow<Resource<Boolean>>
}