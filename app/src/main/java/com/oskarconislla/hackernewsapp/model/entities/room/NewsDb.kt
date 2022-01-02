package com.oskarconislla.hackernewsapp.model.entities.room


import androidx.room.Database

import androidx.room.RoomDatabase

@Database(
    entities = [NewRoom::class],
    version = 2
)

abstract class NewsDb : RoomDatabase() {

    abstract fun newDao(): NewDao

}