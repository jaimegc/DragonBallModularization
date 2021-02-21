package com.jaimegc.dragonballmodularization.libraries.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jaimegc.dragonballmodularization.libraries.local.dao.DragonBallDao
import com.jaimegc.dragonballmodularization.libraries.local.dao.FavoritesDao
import com.jaimegc.dragonballmodularization.libraries.local.entity.DragonBallInfoConverter
import com.jaimegc.dragonballmodularization.libraries.local.entity.DragonBallInfoEntity

@Database(entities = [DragonBallInfoEntity::class], version = 1, exportSchema = false)
@TypeConverters(DragonBallInfoConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dragonBallDao(): DragonBallDao
    abstract fun favoritesDao(): FavoritesDao

    companion object {
        fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "dragonball_modularization.db"
            ).build()
    }
}