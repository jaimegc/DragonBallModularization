package com.jaimegc.dragonballmodularization.features.dragonball_list.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
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

    private val dragonBallAdapter by lazy {
        DragonBallRecyclerAdapter(dragonBallCellActionsDelegate = dragonBallCellViewModel)
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDragonballListBinding
        get() = FragmentDragonballListBinding::inflate

    override fun setup() {
        with(binding.rvDragonBallList) {
            layoutManager = GridLayoutManager(activity, 1)
            adapter = dragonBallAdapter
        }

        bindViewModels()
    }

    private fun bindViewModels() {
        dragonBallListViewModel.dragonBallStateFlow.asLiveData()
            .observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Loading -> {
                        binding.progressView.visible()
                        binding.rvDragonBallList.gone()
                    }
                    is Resource.Success -> {
                        fillDragonBallWithData(it.data)
                        cacheStateViewModel.updateCachedDataState(
                            it.source != DataSource.REMOTE
                        )
                    }
                    is Resource.Failure -> {
                        binding.progressView.gone()
                        requireContext().showErrorDialog(
                            it.failureData.message ?: getString(R.string.generic_error)
                        )
                    }
                    is Resource.None -> { }
                }
            }

        dragonBallCellViewModel.openDragonBallDetails.observe(this) {
            NavigationActions.navigateToDragonBallDetailsScreen(
                context = requireContext(), dragonBallId = it.id, noAnimation = false
            )
        }
    }

    private fun fillDragonBallWithData(list: List<DragonBallInfo>?) {
        binding.progressView.gone()
        binding.rvDragonBallList.visible()
        dragonBallAdapter.addDragonBallList(list ?: mutableListOf())
    }

    override fun onResume() {
        super.onResume()
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        activity?.title = getString(R.string.toolbar_modularization_title)
    }
}