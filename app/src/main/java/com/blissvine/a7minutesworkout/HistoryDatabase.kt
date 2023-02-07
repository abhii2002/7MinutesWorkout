package com.blissvine.a7minutesworkout

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [HistoryEntity::class], version = 1)
abstract  class HistoryDatabase: RoomDatabase(){
    abstract fun  historyDao():  HistoryDao
    //we need to define a companion object which allows us to add functions on the employee database class
    companion object {
        @Volatile
        private var INSTANCE: HistoryDatabase? = null

        //Helper function to get database (so if a database has already been retrieved, the previous
        // database will be returned. otherwise we are going to create a new database.

        fun getInstance(context: Context): HistoryDatabase{
            synchronized(this){
                var instance = INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        HistoryDatabase::class.java,
                        "history_database"
                    ).fallbackToDestructiveMigration().build()

                    INSTANCE = instance
                }
                return instance
            }
        }

    }

}