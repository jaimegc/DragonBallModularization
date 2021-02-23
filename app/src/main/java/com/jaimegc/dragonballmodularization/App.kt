package com.jaimegc.dragonballmodularization

import android.app.Application
import com.jaimegc.dragonballmodularization.features.dragonball_details.di.getDragonBallDetailsModule
import com.jaimegc.dragonballmodularization.features.dragonball_favorites.di.getFavoriteDragonBallModules
import com.jaimegc.dragonballmodularization.features.dragonball_list.di.getDragonBallListModule
import com.jaimegc.dragonballmodularization.features.home.di.getHomeModules
import com.jaimegc.dragonballmodularization.libraries.local.di.getLocalModule
import com.jaimegc.dragonballmodularization.libraries.remote.di.getRemoteModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val list = listOf(
            getRemoteModule(BuildConfig.BASE_URL, BuildConfig.DEBUG),
            getLocalModule(),
            getHomeModules(),
            getDragonBallListModule(),
            getDragonBallDetailsModule(),
            getFavoriteDragonBallModules()
        )

        startKoin {
            modules(list)
            androidContext(this@App)
        }
    }
}