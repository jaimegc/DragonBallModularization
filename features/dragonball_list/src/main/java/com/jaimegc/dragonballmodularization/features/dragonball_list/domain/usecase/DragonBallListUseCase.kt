package com.jaimegc.dragonballmodularization.features.dragonball_list.domain.usecase

import com.jaimegc.dragonballmodularization.features.dragonball_list.domain.repository.DragonBallListRepository
import com.jaimegc.dragonballmodularization.libraries.base.data.DragonBallInfo
import com.jaimegc.dragonballmodularization.libraries.base.data.Resource
import com.jaimegc.dragonballmodularization.libraries.base.domain.FlowUseCase
import kotlinx.coroutines.flow.Flow

internal class DragonBallListUseCase(
    private val repository: DragonBallListRepository
) : FlowUseCase<Nothing?, List<DragonBallInfo>>() {
    override suspend fun execute(parameters: Nothing?): Flow<Resource<List<DragonBallInfo>>> =
        repository.getDragonBall()
}
