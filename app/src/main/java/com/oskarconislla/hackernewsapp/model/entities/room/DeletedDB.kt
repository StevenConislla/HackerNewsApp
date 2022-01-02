package com.oskarconislla.hackernewsapp.model.entities.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Deleted::class],
    version = 1
)
abstract class DeletedDB : RoomDatabase() {
     abstract fun deletedDao(): DeletedDao
}