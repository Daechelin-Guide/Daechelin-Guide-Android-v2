package com.dgsw.guidedaechelin.data.database.review

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ReviewEntity::class],version = 1, exportSchema = false)

abstract class ReviewDatabase : RoomDatabase() {

    abstract fun reviewDao() : ReviewDao

    companion object {

        private var instance : ReviewDatabase? = null

        @Synchronized
        fun getInstance(context : Context):ReviewDatabase?{

            if (instance == null ){

                synchronized(ReviewDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,ReviewDatabase::class.java,"review-database"
                    ).build()

                }
            }
            return instance

        }
    }
}