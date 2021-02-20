package com.jaimegc.dragonballmodularization.libraries.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.jaimegc.dragonballmodularization.libraries.local.entity.DragonBallInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {

    @Query("SELECT * from dragonball_info WHERE isFav")
    fun getAllFavorites(): Flow<List<DragonBallInfoEntity>>

    @Query("SELECT * from dragonball_info WHERE id =:dragonBallId")
    suspend fun getDragonBall(dragonBallId: Long): DragonBallInfoEntity?

    @Query("UPDATE  dragonball_info SET isFav =:newFavState WHERE id =:dragonBallId")
    suspend fun updateDragonBallFav(dragonBallId: Long, newFavState: Boolean)

    @Transaction
    suspend fun toggleDragonBallFavState(dragonBallId: Long): Boolean {
        val dragonBall = getDragonBall(dragonBallId)
        val toggleValue = dragonBall?.isFav?.not() ?: false
        updateDragonBallFav(dragonBallId, toggleValue)

        return true
    }
}