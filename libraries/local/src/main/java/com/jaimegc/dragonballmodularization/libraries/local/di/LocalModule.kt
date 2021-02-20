package com.jaimegc.dragonballmodularization.libraries.local.di

import com.jaimegc.dragonballmodularization.libraries.local.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

fun getLocalModule() = module {
    single { AppDatabase.buildDatabase(androidContext()) }
    factory { get<AppDatabase>().dragonBallDao() }
    factory { get<AppDatabase>().favoritesDao() }
}