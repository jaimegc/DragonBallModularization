package com.jaimegc.dragonballmodularization.features.home.di

import com.jaimegc.dragonballmodularization.features.home.presentation.HomeFragment
import org.koin.dsl.module

fun getHomeModules() = module {
    single {
        HomeFragment.newInstance()
    }
}
