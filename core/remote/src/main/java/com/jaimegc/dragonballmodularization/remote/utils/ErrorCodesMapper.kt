package com.jaimegc.dragonballmodularization.remote.utils

import android.content.Context
import com.jaimegc.dragonballmodularization.remote.R
import com.jaimegc.dragonballmodularize.core.base.util.NetworkCodes
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object ErrorCodesMapper : KoinComponent {
    private val appContext: Context by inject()

    fun getMessage(errorCode: Int) = when (errorCode) {
        NetworkCodes.CONNECTION_ERROR,
        NetworkCodes.TIMEOUT_ERROR -> appContext.getString(R.string.connection_time_out)
        else -> appContext.getString(R.string.generic_error)
    }
}
