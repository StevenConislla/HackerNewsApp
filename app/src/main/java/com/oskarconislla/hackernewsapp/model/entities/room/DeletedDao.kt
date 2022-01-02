package com.oskarconislla.hackernewsapp.model.entities.room

import androidx.room.*

@Dao
interface DeletedDao {
    @Query("SELECT * FROM Deleted")
    fun getAll(): List<Deleted>

    @Insert
    fun insertOne(deleted:Deleted)


    @Delete
    fun delete(deleted:Deleted)

    @Query("DELETE FROM Deleted")
    fun deleteAll()


}