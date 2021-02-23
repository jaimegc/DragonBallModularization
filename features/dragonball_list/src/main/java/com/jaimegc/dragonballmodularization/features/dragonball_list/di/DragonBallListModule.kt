package com.jaimegc.dragonballmodularization.features.dragonball_list.di

import com.jaimegc.dragonballmodularization.features.dragonball_list.data.DragonBallListRepositoryImpl
import com.jaimegc.dragonballmodularization.features.dragonball_list.data.datasources.local.DragonBallListLocalSource
import com.jaimegc.dragonballmodularization.features.dragonball_list.data.datasources.local.DragonBallListLocalSourceImpl
import com.jaimegc.dragonballmodularization.features.dragonball_list.data.datasources.remote.DragonBallListApi
import com.jaimegc.dragonballmodularization.features.dragonball_list.data.datasources.remote.DragonBallListRemoteSource
import com.jaimegc.dragonballmodularization.features.dragonball_list.data.datasources.remote.DragonBallListRemoteSourceImpl
import com.jaimegc.dragonballmodularization.features.dragonball_list.domain.repository.DragonBallListRepository
import com.jaimegc.dragonballmodularization.features.dragonball_list.domain.usecase.DragonBallListUseCase
import com.jaimegc.dragonballmodularization.features.dragonball_list.presentation.CacheStateSharedViewModel
import com.jaimegc.dragonballmodularization.features.dragonball_list.presentation.DragonBallListViewModel
import com.jaimegc.dragonballmodularization.libraries.base.util.ApplicationDispatchersProvider
import com.jaimegc.dragonballmodularization.libraries.base.util.SchedulerProvider
import com.jaimegc.dragonballmodularization.libraries.ui_components.viewmodel.DragonBallCellViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

fun getDragonBallListModule() = module {
    single {
        get<Retrofit>().create(DragonBallListApi::class.java)
    }

    factory<DragonBallListLocalSource> {
        DragonBallListLocalSourceImpl(get())
    }

    factory<DragonBallListRemoteSource> {
        DragonBallListRemoteSourceImpl(get())
    }

    factory<SchedulerProvider> { ApplicationDispatchersProvider() }

    single<DragonBallListRepository> {
        DragonBallListRepositoryImpl(get(), get(), get())
    }

    factory {
        DragonBallListUseCase(get())
    }

    viewModel {
        DragonBallCellViewModel()
    }

    viewModel {
        DragonBallListViewModel(get())
    }

    viewModel {
        CacheStateSharedViewModel()
    }
}
