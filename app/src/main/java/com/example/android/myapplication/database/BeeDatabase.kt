package com.example.android.myapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BeeGroup::class, Beehive::class], version = 10, exportSchema = false)
abstract class BeeDatabase: RoomDatabase() {

    abstract val beeDatabaseDao: BeeDatabaseDao

    companion object{

        @Volatile
        private var INSTANCE: BeeDatabase? = null


        fun getInstance(context: Context): BeeDatabase{
            synchronized(this){

                var instance = INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(context.applicationContext,
                        BeeDatabase::class.java,"bee_history_database")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance

                }
                return instance
            }

        }
    }
}
