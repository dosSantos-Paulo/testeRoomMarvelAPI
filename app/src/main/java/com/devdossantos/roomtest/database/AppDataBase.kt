package com.devdossantos.roomtest.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.devdossantos.roomtest.card.entity.CardEntity
import com.devdossantos.roomtest.card.dao.CardDao

@Database(
    entities = [CardEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase: RoomDatabase() {

    abstract fun cardDao(): CardDao

    companion object{

        private var INSTANCE: AppDataBase? = null
        fun getDatabase(context: Context): AppDataBase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "marvel_my_hero"
                ).build()
            }

            return INSTANCE!!

        }
    }
}