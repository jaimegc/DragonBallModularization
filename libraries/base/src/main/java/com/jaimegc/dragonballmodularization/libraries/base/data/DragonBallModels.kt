package com.jaimegc.dragonballmodularization.libraries.base.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DragonBallResponse(
    @Json(name = "request_hash") val hash: String,
    @Json(name = "request_cached") val cached: Boolean,
    @Json(name = "request_cache_expiry") val cacheExpired: Long,
    @Json(name = "last_page") val lastPage: Long,
    @Json(name = "results") val results: List<DragonBallInfo>
)

@JsonClass(generateAdapter = true)
data class DragonBallInfo(
    @Json(name = "mal_id") val id: Long,
    @Json(name = "url") val url: String,
    @Json(name = "image_url") val imageUrl: String,
    @Json(name = "title") val title: String,
    @Json(name = "airing") val airing: Boolean,
    @Json(name = "synopsis") val synopsis: String,
    @Json(name = "type") val type: String,
    @Json(name = "score") val score: Double,
    @Json(name = "episodes") val episodes: Int,
    @Json(name = "start_date") val startDate: String,
    @Json(name = "end_date") val endDate: String?,
    @Json(name = "members") val members: Long,
    @Json(name = "rated") val rated: String?,
    @Json(name = "isFav") var isFav: Boolean = false
) {
    fun toggleFav() {
        this.isFav = isFav.not()
    }
}