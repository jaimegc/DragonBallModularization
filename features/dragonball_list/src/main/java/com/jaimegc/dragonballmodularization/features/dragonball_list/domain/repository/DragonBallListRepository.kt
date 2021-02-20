package com.jaimegc.dragonballmodularization.features.dragonball_list.domain.repository

import com.jaimegc.dragonballmodularization.libraries.base.data.DragonBallInfo
import com.jaimegc.dragonballmodularization.libraries.base.data.Resource
import kotlinx.coroutines.flow.Flow

internal interface DragonBallListRepository {
    suspend fun getDragonBall(): Flow<Resource<List<DragonBallInfo>>>
}
