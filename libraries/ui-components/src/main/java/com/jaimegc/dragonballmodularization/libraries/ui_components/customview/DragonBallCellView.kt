package com.jaimegc.dragonballmodularization.libraries.ui_components.customview

import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.jaimegc.dragonballmodularization.libraries.base.data.DragonBallInfo
import com.jaimegc.dragonballmodularization.libraries.ui_components.databinding.ViewDragonballCellBinding
import com.jaimegc.dragonballmodularization.libraries.ui_components.util.GlideApp

class DragonBallCellView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1
) : FrameLayout(context, attrs, defStyleAttr) {

    private var binding: ViewDragonballCellBinding = ViewDragonballCellBinding.inflate(
        LayoutInflater.from(context),
        this,
        false
    )

    init {
        addView(binding.root)
    }

    fun initDetails(dragonBallInfo: DragonBallInfo, actionDelegate: DragonBallCellActionsDelegate) {
        binding.tvDragonBallTitle.text = dragonBallInfo.title
        binding.tvDragonBallType.text = dragonBallInfo.type

        GlideApp.with(context)
            .asBitmap()
            .load(dragonBallInfo.imageUrl)
            .listener(object : RequestListener<Bitmap> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }
            }).into(binding.ivDragonBallPic)

        binding.root.setOnClickListener {
            actionDelegate.onCellClicked(dragonBallInfo)
        }
    }

    interface DragonBallCellActionsDelegate {
        fun onCellClicked(dragonBall: DragonBallInfo)
    }
}
