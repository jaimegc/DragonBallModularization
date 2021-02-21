package com.jaimegc.dragonballmodularization.features.dragonball_details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaimegc.dragonballmodularization.features.dragonball_details.domain.usecase.DragonBallDetailsUseCase
import com.jaimegc.dragonballmodularization.features.dragonball_details.domain.usecase.FavoriteDragonBallToggleUseCase
import com.jaimegc.dragonballmodularization.libraries.base.data.DragonBallInfo
import com.jaimegc.dragonballmodularization.libraries.base.data.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

internal class DragonBallDetailsViewModel(
    private val useCaseDetails: DragonBallDetailsUseCase,
    private val useCaseFavoriteToggle: FavoriteDragonBallToggleUseCase
) : ViewModel() {

    private val _dragonBallDetailsStateFlow: MutableStateFlow<Resource<DragonBallInfo>> =
        MutableStateFlow(Resource.Loading)

    val dragonBallDetailsStateFlow: StateFlow<Resource<DragonBallInfo>> = _dragonBallDetailsStateFlow

    private val _favDragonBallToggleStateFlow: MutableStateFlow<Resource<Boolean>> =
        MutableStateFlow(Resource.Loading)

    val favDragonBallToggleStateFlow: StateFlow<Resource<Boolean>> = _favDragonBallToggleStateFlow

    fun getDragonBallDetails(dragonBallId: Long) {
        viewModelScope.launch {
            useCaseDetails.invoke(dragonBallId)
                .collect {
                    _dragonBallDetailsStateFlow.value = it
                }
        }
    }

    fun toggleFavoriteDragonBall(dragonBallId: Long) {
        viewModelScope.launch {
            useCaseFavoriteToggle(dragonBallId)
                .collect {
                    _favDragonBallToggleStateFlow.value = it
                }
        }
    }
}