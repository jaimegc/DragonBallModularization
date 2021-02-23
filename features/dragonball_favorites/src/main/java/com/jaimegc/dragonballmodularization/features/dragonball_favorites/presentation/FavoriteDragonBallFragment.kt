package com.jaimegc.dragonballmodularization.features.dragonball_favorites.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.GridLayoutManager
import com.jaimegc.dragonballmodularization.features.dragonball_favorites.R
import com.jaimegc.dragonballmodularization.features.dragonball_favorites.databinding.FragmentFavoriteDragonballBinding
import com.jaimegc.dragonballmodularization.libraries.base.data.DragonBallInfo
import com.jaimegc.dragonballmodularization.libraries.base.data.Resource
import com.jaimegc.dragonballmodularization.libraries.base.ui.BaseFragment
import com.jaimegc.dragonballmodularization.libraries.navigator.NavigationActions
import com.jaimegc.dragonballmodularization.libraries.ui_components.adapter.DragonBallRecyclerAdapter
import com.jaimegc.dragonballmodularization.libraries.ui_components.util.gone
import com.jaimegc.dragonballmodularization.libraries.ui_components.util.showErrorDialog
import com.jaimegc.dragonballmodularization.libraries.ui_components.util.visible
import com.jaimegc.dragonballmodularization.libraries.ui_components.viewmodel.DragonBallCellViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteDragonBallFragment : BaseFragment<FragmentFavoriteDragonballBinding>() {

    companion object {
        fun newInstance() = FavoriteDragonBallFragment()
    }

    private val dragonBallCellViewModel: DragonBallCellViewModel by viewModel()
    private val favoriteDragonBallViewModel: FavoriteDragonBallViewModel by viewModel()

    private val dragonBallAdapter by lazy {
        DragonBallRecyclerAdapter(dragonBallCellActionsDelegate = dragonBallCellViewModel)
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFavoriteDragonballBinding
        get() = FragmentFavoriteDragonballBinding::inflate

    override fun setup() {
        with(binding.rvDragonBall) {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = dragonBallAdapter
        }

        bindViewModels()
    }

    private fun bindViewModels() {
        favoriteDragonBallViewModel.dragonBallStateFlow.asLiveData().observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    with(binding) {
                        progress.visible()
                        tvNoFavs.gone()
                        rvDragonBall.gone()
                    }
                }
                is Resource.Success -> {
                    binding.progress.gone()
                    it.data
                        ?.takeIf { list -> list.isNotEmpty() }
                        ?.let { dragonBallList ->
                            binding.tvNoFavs.gone()
                            fillViewWithData(dragonBallList)
                        } ?: kotlin.run {
                        binding.rvDragonBall.gone()
                        binding.tvNoFavs.visible()
                    }
                }
                is Resource.Failure -> {
                    binding.progress.gone()
                    requireContext().showErrorDialog(
                        it.failureData.message ?: getString(R.string.generic_error)
                    )
                }
                is Resource.None -> { }
            }
        }
        dragonBallCellViewModel.openDragonBallDetails.observe(this) {
            NavigationActions.navigateToDragonBallDetailsScreen(requireContext(), dragonBallId = it.id)
        }
    }

    private fun fillViewWithData(list: List<DragonBallInfo>) {
        binding.rvDragonBall.visible()
        dragonBallAdapter.addDragonBallList(list)
    }
}