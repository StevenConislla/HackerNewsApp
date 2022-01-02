package com.oskarconislla.hackernewsapp.model.entities.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NewRoom (
    @PrimaryKey
    val objectID: String,

    val story_title: String,

    val author: String,

    val created_at: String,

    val story_url: String,


        )