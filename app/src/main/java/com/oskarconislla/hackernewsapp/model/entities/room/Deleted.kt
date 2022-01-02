package com.oskarconislla.hackernewsapp.model.entities.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Deleted (
    @PrimaryKey
    val objectID: String
)