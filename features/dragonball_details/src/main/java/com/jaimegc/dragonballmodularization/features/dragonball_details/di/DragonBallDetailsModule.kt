package com.jaimegc.dragonballmodularization.features.dragonball_details.di

import com.jaimegc.dragonballmodularization.features.dragonball_details.data.DragonBallDetailsRepositoryImpl
import com.jaimegc.dragonballmodularization.features.dragonball_details.data.datasource.local.DragonBallDetailsLocalDataSource
import com.jaimegc.dragonballmodularization.features.dragonball_details.data.datasource.local.DragonBallDetailsLocalDataSourceImpl
import com.jaimegc.dragonballmodularization.features.dragonball_details.domain.repository.DragonBallDetailsRepository
import com.jaimegc.dragonballmodularization.features.dragonball_details.domain.usecase.DragonBallDetailsUseCase
import com.jaimegc.dragonballmodularization.features.dragonball_details.domain.usecase.FavoriteDragonBallToggleUseCase
import com.jaimegc.dragonballmodularization.features.dragonball_details.presentation.DragonBallDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun getDragonBallDetailsModule() = module {

    factory<DragonBallDetailsLocalDataSource> {
        DragonBallDetailsLocalDataSourceImpl(get(), get())
    }

    single<DragonBallDetailsRepository> {
        DragonBallDetailsRepositoryImpl(get(), get())
    }

    factory {
        DragonBallDetailsUseCase(get())
    }

    factory {
        FavoriteDragonBallToggleUseCase(get())
    }

    viewModel {
        DragonBallDetailsViewModel(get(), get())
    }
}