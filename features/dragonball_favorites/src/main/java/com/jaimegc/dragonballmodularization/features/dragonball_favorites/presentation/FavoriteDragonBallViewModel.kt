package com.jaimegc.dragonballmodularization.features.dragonball_favorites.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaimegc.dragonballmodularization.features.dragonball_favorites.domain.usecase.FavoriteDragonBallInquiryUseCase
import com.jaimegc.dragonballmodularization.libraries.base.data.DragonBallInfo
import com.jaimegc.dragonballmodularization.libraries.base.data.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FavoriteDragonBallViewModel(
    private val inquiryUseCase: FavoriteDragonBallInquiryUseCase
) : ViewModel() {
    private val _dragonBallStateFlow: MutableStateFlow<Resource<List<DragonBallInfo>>> =
        MutableStateFlow(Resource.Loading)

    val dragonBallStateFlow: StateFlow<Resource<List<DragonBallInfo>>> = _dragonBallStateFlow

    init {
        getFavoriteDragonBall()
    }

    private fun getFavoriteDragonBall() {
        viewModelScope.launch {
            inquiryUseCase()
                .collect {
                    _dragonBallStateFlow.value = it
                }
        }
    }
}
