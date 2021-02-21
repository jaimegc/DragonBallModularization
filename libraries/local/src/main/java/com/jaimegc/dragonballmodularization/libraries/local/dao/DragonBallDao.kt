package com.jaimegc.dragonballmodularization.libraries.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jaimegc.dragonballmodularization.libraries.local.entity.DragonBallInfoEntity

@Dao
interface DragonBallDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(dragonBallList: List<DragonBallInfoEntity>)

    @Query("SELECT * from dragonball_info")
    suspend fun getAll(): List<DragonBallInfoEntity>

    @Query("SELECT * from dragonball_info WHERE id =:dragonBallId")
    suspend fun getDragonBall(dragonBallId: Long): DragonBallInfoEntity?
}