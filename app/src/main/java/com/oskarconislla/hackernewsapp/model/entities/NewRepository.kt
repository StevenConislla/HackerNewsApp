package com.oskarconislla.hackernewsapp.model.entities

import android.content.Context
import androidx.lifecycle.MutableLiveData

interface NewRepository {
    fun getNews(): MutableLiveData<List<New>>
    fun callNewsAPI(context: Context)
    fun callNewsRoom(context: Context)
    fun deleteItemAt(position: Int)
}