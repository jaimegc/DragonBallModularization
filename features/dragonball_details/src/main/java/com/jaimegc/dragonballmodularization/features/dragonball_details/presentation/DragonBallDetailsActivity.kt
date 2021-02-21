package com.jaimegc.dragonballmodularization.features.dragonball_details.presentation

import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import androidx.lifecycle.asLiveData
import androidx.palette.graphics.Palette
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.jaimegc.dragonballmodularization.features.dragonball_details.R
import com.jaimegc.dragonballmodularization.features.dragonball_details.databinding.ActivityDragonballDetailsBinding
import com.jaimegc.dragonballmodularization.libraries.base.data.DragonBallInfo
import com.jaimegc.dragonballmodularization.libraries.base.data.Resource
import com.jaimegc.dragonballmodularization.libraries.base.ui.BaseActivity
import com.jaimegc.dragonballmodularization.libraries.navigator.ActionKeys
import com.jaimegc.dragonballmodularization.libraries.ui_components.util.GlideApp
import com.jaimegc.dragonballmodularization.libraries.ui_components.util.gone
import com.jaimegc.dragonballmodularization.libraries.ui_components.util.showErrorDialog
import com.jaimegc.dragonballmodularization.libraries.ui_components.util.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class DragonBallDetailsActivity : BaseActivity<ActivityDragonballDetailsBinding>() {

    private val dragonBallDetailsViewModel: DragonBallDetailsViewModel by viewModel()

    override val bindingInflater: (LayoutInflater) -> ActivityDragonballDetailsBinding
        get() = ActivityDragonballDetailsBinding::inflate

    override fun setup() {
        attachActions()

        bindViewModels(intent.extras?.getLong(ActionKeys.DRAGONBALL_ID_KEY))
    }

    private fun attachActions() {
        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnFav.setOnClickListener { favBtnView ->
            handleFavAction(favBtnView)
        }
    }

    private fun bindViewModels(dragonBallId: Long?) {
        dragonBallDetailsViewModel.dragonBallDetailsStateFlow.asLiveData().observe(this) {
            when (it) {
                is Resource.Loading -> {
                    binding.progress.visible()
                }
                is Resource.Success -> {
                    binding.progress.gone()
                    binding.viewsGroup.visible()

                    it.data?.let { dragonBallInfo ->
                        fillDragonBallDetails(dragonBallInfo)
                    }
                }
                is Resource.Failure -> {
                    binding.progress.gone()
                    this.showErrorDialog(
                        it.failureData.message ?: getString(R.string.generic_error)
                    )
                }
                is Resource.None -> {
                    binding.progress.gone()
                }
            }
        }

        dragonBallDetailsViewModel.getDragonBallDetails(dragonBallId ?: -1L)
    }

    private fun fillDragonBallDetails(dragonBallInfo: DragonBallInfo) {
        with(binding) {
            configDragonBallImage(dragonBallInfo.imageUrl)
            tvDragonBallName.text = dragonBallInfo.title
            tvRoleType.text = dragonBallInfo.type
            btnFav.setImageResource(dragonBallInfo.isFav.getFavoriteImage())
            tvBiographyText.text = dragonBallInfo.rated
        }
    }

    private fun handleFavAction(favBtnView: View?) {
        if (dragonBallDetailsViewModel.dragonBallDetailsStateFlow.value is Resource.Success<*>) {
            val dragonBallInfoData =
                (dragonBallDetailsViewModel.dragonBallDetailsStateFlow.value
                        as Resource.Success<DragonBallInfo>).data
            with(dragonBallInfoData) {
                dragonBallDetailsViewModel.toggleFavoriteDragonBall(this?.id ?: -1L)
                this?.toggleFav()
                (favBtnView as ImageButton).setImageResource(
                    this?.isFav?.getFavoriteImage() ?: 0
                )
            }
        }
    }

    private fun configDragonBallImage(bustPortrait: String) {
        GlideApp.with(this)
            .asBitmap()
            .load(bustPortrait)
            .listener(object : RequestListener<Bitmap> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Bitmap>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Bitmap>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    resource?.let { bitmap ->
                        Palette.from(bitmap).generate {
                            // Use the dominant color in image
                            val dominantColor =
                                it?.getMutedColor(
                                    ContextCompat.getColor(
                                        this@DragonBallDetailsActivity,
                                        R.color.white
                                    )
                                )
                                    ?: 0x000
                            binding.ivBackground.backgroundTintList =
                                ColorStateList.valueOf(dominantColor)
                        }
                    }
                    return false
                }
            }).into(binding.ivDragonBallPic)
    }

    private fun Boolean.getFavoriteImage() = if (this) {
        R.drawable.iv_selected_fav
    } else {
        R.drawable.ic_unselected_fav_24
    }
}