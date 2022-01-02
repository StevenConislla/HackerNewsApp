package com.oskarconislla.hackernewsapp.model.entities.room

import android.app.Application
import androidx.room.Room

class NewApp : Application() {
    lateinit var room : NewsDb
    lateinit var deletedDB : DeletedDB
    override fun onCreate() {
        super.onCreate()
         room = Room.databaseBuilder(this,NewsDb::class.java,"NewRoom").allowMainThreadQueries().fallbackToDestructiveMigration().build()
        deletedDB = Room.databaseBuilder(this,DeletedDB::class.java,"Deleted").allowMainThreadQueries().fallbackToDestructiveMigration().build()

    }


}