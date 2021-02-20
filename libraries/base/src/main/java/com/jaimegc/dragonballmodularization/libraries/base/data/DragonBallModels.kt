package com.jaimegc.dragonballmodularization.libraries.base.data

import com.squareup.moshi.Json

data class DragonBallResponse(
    @field:Json(name = "request_hash") val hash: String,
    @field:Json(name = "request_cached") val cached: Boolean,
    @field:Json(name = "request_cache_expiry") val cacheExpired: Long,
    @field:Json(name = "last_page") val lastPage: Long,
    @field:Json(name = "results") val results: List<DragonBallInfo>
)

data class DragonBallInfo(
    @field:Json(name = "mal_id") val id: Long,
    @field:Json(name = "url") val url: String,
    @field:Json(name = "image_url") val imageUrl: String,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "airing") val airing: Boolean,
    @field:Json(name = "synopsis") val synopsis: String,
    @field:Json(name = "type") val type: String,
    @field:Json(name = "score") val score: Int,
    @field:Json(name = "episodes") val episodes: Int,
    @field:Json(name = "start_date") val startDate: String,
    @field:Json(name = "end_date") val endDate: String,
    @field:Json(name = "members") val members: Long,
    @field:Json(name = "rated") val rated: String,
)