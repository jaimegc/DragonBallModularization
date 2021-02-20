package com.jaimegc.dragonballmodularization.features.dragonball_list.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.GridLayoutManager
import com.jaimegc.dragonballmodularization.features.dragonball_list.R
import com.jaimegc.dragonballmodularization.features.dragonball_list.databinding.FragmentDragonballListBinding
import com.jaimegc.dragonballmodularization.libraries.base.data.DataSource
import com.jaimegc.dragonballmodularization.libraries.base.data.DragonBallInfo
import com.jaimegc.dragonballmodularization.libraries.base.data.Resource
import com.jaimegc.dragonballmodularization.libraries.base.ui.BaseFragment
import com.jaimegc.dragonballmodularization.libraries.navigator.NavigationActions
import com.jaimegc.dragonballmodularization.libraries.ui_components.adapter.DragonBallRecyclerAdapter
import com.jaimegc.dragonballmodularization.libraries.ui_components.util.gone
import com.jaimegc.dragonballmodularization.libraries.ui_components.util.showErrorDialog
import com.jaimegc.dragonballmodularization.libraries.ui_components.util.visible
import com.jaimegc.dragonballmodularization.libraries.ui_components.viewmodel.DragonBallCellViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DragonBallListFragment : BaseFragment<FragmentDragonballListBinding>() {

    companion object {
        fun newInstance() = DragonBallListFragment()
    }

    private val dragonBallCellViewModel: DragonBallCellViewModel by viewModel()
    private val dragonBallListViewModel: DragonBallListViewModel by viewModel()
    private val cacheStateViewModel by lazy {
        requireActivity().getViewModel<CacheStateSharedViewModel>()
    }

    private val navigator: NavigationActions by inject()

    private val agentsAdapter by lazy {
        DragonBallRecyclerAdapter(dragonBallCellActionsDelegate = dragonBallCellViewModel)
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDragonballListBinding
        get() = FragmentDragonballListBinding::inflate

    override fun setup() {
        // Setup views
        with(binding.rvAgentsList) {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = agentsAdapter
        }

        bindViewModels()
    }

    private fun bindViewModels() {
        dragonBallListViewModel.dragonBallStateFlow.asLiveData()
            .observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Loading -> {
                        binding.progressView.visible()
                        binding.rvAgentsList.gone()
                    }
                    is Resource.Success -> {
                        fillDragonBallWithData(it.data)
                        cacheStateViewModel.updateCachedDataState(
                            it.source != DataSource.REMOTE
                        )
                    }
                    is Resource.Failure -> {
                        // Show error toast & hide progress
                        binding.progressView.gone()
                        requireContext().showErrorDialog(
                            it.failureData.message ?: getString(R.string.generic_error)
                        )
                    }
                    is Resource.None -> { }
                }
            }

        dragonBallCellViewModel.openDragonBallDetails.observe(this) {
            navigator.navigateToDragonBallDetailsScreen(requireContext(), dragonBallId = it.id)
        }
    }

    private fun fillDragonBallWithData(list: List<DragonBallInfo>?) {
        binding.progressView.gone()
        binding.rvAgentsList.visible()
        agentsAdapter.addDragonBalls(list ?: mutableListOf())
    }
}
