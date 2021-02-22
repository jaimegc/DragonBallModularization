package com.jaimegc.dragonballmodularization.features.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jaimegc.dragonballmodularization.features.home.R
import com.jaimegc.dragonballmodularization.features.home.databinding.FragmentHomeBinding
import com.jaimegc.dragonballmodularization.libraries.base.ui.BaseFragment
import com.jaimegc.dragonballmodularization.libraries.ui_components.util.setupWithNavController

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val navGraphIds = listOf(
        R.navigation.navigation_dragonball_list_graph,
        R.navigation.navigation_dragonball_favorites_graph
    )

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override fun setup() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }
    }

    private fun setupBottomNavigationBar() {
        val navController = binding.bottomNavigation.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = childFragmentManager,
            containerId = R.id.nav_host_container,
            intent = requireActivity().intent
        )

        /*navController.observe(
            viewLifecycleOwner,
            Observer {
                viewModel.navigationControllerChanged(it)
                NavigationUI.setupActionBarWithNavController(requireCompatActivity(), it)
            }
        )*/
    }
}