package com.jaimegc.dragonballmodularization.libraries.ui_components.util

import android.view.View
import android.widget.ImageView
import com.jaimegc.dragonballmodularization.libraries.ui_components.R

fun View.gone() {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
}

fun View.visible() {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
}

fun ImageView.loadImage(url: String?) {
    if (url?.isNotEmpty() == true) {
        GlideApp.with(context)
            .load(url)
            .error(R.drawable.ic_broken_image_24)
            .into(this)
    } else
        GlideApp.with(context)
            .load(R.drawable.ic_broken_image_24)
            .into(this)
}
