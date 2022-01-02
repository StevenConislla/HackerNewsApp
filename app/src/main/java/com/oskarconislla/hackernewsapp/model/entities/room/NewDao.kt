package com.oskarconislla.hackernewsapp.model.entities.room


import androidx.room.*


@Dao
interface NewDao {
    @Query("SELECT * FROM NewRoom")
    fun getAll(): List<NewRoom>

    @Query("SELECT * FROM NewRoom WHERE objectID = :objectID")
    fun getById(objectID:String): NewRoom

    @Update
    fun update(newRoom: NewRoom)

    @Insert
    fun insert(newRoom:List<NewRoom>)

    @Insert
    fun insertOne(newRoom:NewRoom)


    @Delete
    fun delete(newRoom: NewRoom)

    @Query("DELETE FROM NewRoom")
    fun deleteAll()


}