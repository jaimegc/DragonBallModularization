package com.jaimegc.dragonballmodularization.libraries.ui_components.util

import android.content.Context
import android.widget.Toast

fun Context.showErrorDialog(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT)
        .show()
}
