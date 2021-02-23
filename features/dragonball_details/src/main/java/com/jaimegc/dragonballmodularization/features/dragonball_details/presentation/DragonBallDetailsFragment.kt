package com.jaimegc.dragonballmodularization.features.dragonball_details.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import com.jaimegc.dragonballmodularization.features.dragonball_details.databinding.ActivityDragonballDetailsBinding
import com.jaimegc.dragonballmodularization.libraries.base.ui.BaseFragment

class DragonBallDetailsFragment : BaseFragment<ActivityDragonballDetailsBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> ActivityDragonballDetailsBinding
        get() = ActivityDragonballDetailsBinding::inflate

    override fun setup() {

    }

}