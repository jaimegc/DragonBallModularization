package com.jaimegc.dragonballmodularization.libraries.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.jaimegc.dragonballmodularization.libraries.local.entity.DragonBallInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {

    @Query("SELECT * from dragonball_info WHERE isFav = 1")
    fun getAllFavorites(): Flow<List<DragonBallInfoEntity>>

    @Query("SELECT * from dragonball_info WHERE id =:dragonBallId")
    suspend fun getDragonBall(dragonBallId: String): DragonBallInfoEntity?

    @Query("UPDATE  dragonball_info SET isFav =:newFavState WHERE id =:dragonBallId")
    suspend fun updateDragonBallFav(dragonBallId: String, newFavState: Boolean)

    @Transaction
    suspend fun toggleDragonBallFavState(uuid: String): Boolean {
        val dragonBall = getDragonBall(uuid)
        val toggleValue = dragonBall?.isFav?.not() ?: false
        updateDragonBallFav(uuid, toggleValue)

        return true
    }
}