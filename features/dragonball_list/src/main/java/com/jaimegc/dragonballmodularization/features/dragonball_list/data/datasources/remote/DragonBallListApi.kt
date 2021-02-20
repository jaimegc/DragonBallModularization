package com.jaimegc.dragonballmodularization.features.dragonball_list.data.datasources.remote

import com.jaimegc.dragonballmodularization.libraries.base.data.DragonBallResponse
import retrofit2.http.GET

interface DragonBallListApi {
    @GET("search/anime?q=dragonball")
    suspend fun getDragonBall(): DragonBallResponse
}
