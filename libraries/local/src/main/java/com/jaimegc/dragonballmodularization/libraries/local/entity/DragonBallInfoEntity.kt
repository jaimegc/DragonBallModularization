package com.jaimegc.dragonballmodularization.libraries.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jaimegc.dragonballmodularization.libraries.base.data.DragonBallInfo

@Entity(tableName = "dragonball_info")
data class DragonBallInfoEntity(
    @PrimaryKey
    val id: Long,
    val data: DragonBallInfo,
    var isFav: Boolean = false
)
