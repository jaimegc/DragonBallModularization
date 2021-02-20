package com.jaimegc.dragonballmodularization.features.dragonball_favorites.presentation

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaimegc.dragonballmodularization.features.dragonball_favorites.domain.usecase.FavoriteDragonBallInquiryUseCase
import com.jaimegc.dragonballmodularization.features.dragonball_favorites.domain.usecase.FavoriteDragonBallToggleUseCase
import com.jaimegc.dragonballmodularization.libraries.base.data.DragonBallInfo
import com.jaimegc.dragonballmodularization.libraries.base.data.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FavoriteDragonBallViewModel(
    private val inquiryUseCase: FavoriteDragonBallInquiryUseCase,
    private val toggleUseCase: FavoriteDragonBallToggleUseCase
) : ViewModel() {
    private val _dragonBallStateFlow: MutableStateFlow<Resource<List<DragonBallInfo>>> =
        MutableStateFlow(Resource.Loading)

    val dragonBallStateFlow: StateFlow<Resource<List<DragonBallInfo>>> = _dragonBallStateFlow

    private val _favDragonBallToggleStateFlow: MutableStateFlow<Resource<Boolean>> =
        MutableStateFlow(Resource.Loading)

    val favDragonBallToggleStateFlow: StateFlow<Resource<Boolean>> = _favDragonBallToggleStateFlow

    init {
        getFavoriteDragonBall()
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun getFavoriteDragonBall() {
        viewModelScope.launch {
            inquiryUseCase()
                .collect {
                    _dragonBallStateFlow.value = it
                }
        }
    }

    fun toggleFavoriteDragonBall(dragonBallId: Long) {
        viewModelScope.launch {
            toggleUseCase(dragonBallId)
                .collect {
                    _favDragonBallToggleStateFlow.value = it
                }
        }
    }
}
