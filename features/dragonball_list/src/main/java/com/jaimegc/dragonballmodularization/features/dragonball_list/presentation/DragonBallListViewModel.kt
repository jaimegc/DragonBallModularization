package com.jaimegc.dragonballmodularization.features.dragonball_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaimegc.dragonballmodularization.features.dragonball_list.domain.usecase.DragonBallListUseCase
import com.jaimegc.dragonballmodularization.libraries.base.data.DragonBallInfo
import com.jaimegc.dragonballmodularization.libraries.base.data.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

internal class DragonBallListViewModel(private val useCase: DragonBallListUseCase) : ViewModel() {

    private val _dragonBallStateFlow: MutableStateFlow<Resource<List<DragonBallInfo>>> =
        MutableStateFlow(Resource.Loading)

    val dragonBallStateFlow: StateFlow<Resource<List<DragonBallInfo>>> = _dragonBallStateFlow

    init {
        getPopularAgents()
    }

    private fun getPopularAgents() {
        viewModelScope.launch {
            useCase()
                .collect {
                    _dragonBallStateFlow.value = it
                }
        }
    }
}
