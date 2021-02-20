package com.jaimegc.dragonballmodularization.libraries.local.entity

import androidx.room.TypeConverter
import com.jaimegc.dragonballmodularization.libraries.base.data.DragonBallInfo
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class DragonBallInfoConverter {
    @TypeConverter
    fun fromString(value: String): DragonBallInfo? =
        if (!value.contentEquals("null")) {
            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            val typeCustom =
                Types.newParameterizedType(DragonBallInfo::class.java, DragonBallInfo::class.java)
            moshi.adapter<DragonBallInfo>(typeCustom).fromJson(value)
        } else null

    @TypeConverter
    fun fromHomeResponse(dragonBallInfo: DragonBallInfo?): String? {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val typeCustom = Types.newParameterizedType(DragonBallInfo::class.java, DragonBallInfo::class.java)

        return moshi.adapter<DragonBallInfo>(typeCustom).toJson(dragonBallInfo)
    }
}
