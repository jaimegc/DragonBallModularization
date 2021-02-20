package com.jaimegc.dragonballmodularization.libraries.ui_components.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jaimegc.dragonballmodularization.libraries.base.data.DragonBallInfo
import com.jaimegc.dragonballmodularization.libraries.ui_components.customview.DragonBallCellView
import com.jaimegc.dragonballmodularization.libraries.ui_components.util.SingleLiveData

class DragonBallCellViewModel : ViewModel(), DragonBallCellView.DragonBallCellActionsDelegate {

    private val _openDragonBallDetails: MutableLiveData<DragonBallInfo> = SingleLiveData()
    val openDragonBallDetails: LiveData<DragonBallInfo> = _openDragonBallDetails

    override fun onCellClicked(dragonBall: DragonBallInfo) {
        _openDragonBallDetails.postValue(dragonBall)
    }
}