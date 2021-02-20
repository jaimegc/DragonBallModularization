package com.jaimegc.dragonballmodularization.features.dragonball_favorites.di

import com.jaimegc.dragonballmodularization.features.dragonball_favorites.data.FavoriteDragonBallRepositoryImpl
import com.jaimegc.dragonballmodularization.features.dragonball_favorites.data.datasource.local.FavoritesDragonBallLocalDataSource
import com.jaimegc.dragonballmodularization.features.dragonball_favorites.data.datasource.local.FavoritesDragonBallLocalDataSourceImpl
import com.jaimegc.dragonballmodularization.features.dragonball_favorites.domain.repository.FavoriteDragonBallRepository
import com.jaimegc.dragonballmodularization.features.dragonball_favorites.domain.usecase.FavoriteDragonBallInquiryUseCase
import com.jaimegc.dragonballmodularization.features.dragonball_favorites.domain.usecase.FavoriteDragonBallToggleUseCase
import com.jaimegc.dragonballmodularization.features.dragonball_favorites.presentation.FavoriteDragonBallViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@ExperimentalCoroutinesApi
fun getFavoriteDragonBallModules() = module {

    factory<FavoritesDragonBallLocalDataSource> {
        FavoritesDragonBallLocalDataSourceImpl(get())
    }

    single<FavoriteDragonBallRepository> {
        FavoriteDragonBallRepositoryImpl(get(), get())
    }

    factory {
        FavoriteDragonBallInquiryUseCase(get())
    }
    factory {
        FavoriteDragonBallToggleUseCase(get())
    }

    viewModel {
        FavoriteDragonBallViewModel(get(), get())
    }
}
