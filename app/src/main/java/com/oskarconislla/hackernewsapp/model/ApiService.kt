package com.oskarconislla.hackernewsapp.model

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("search_by_date?query=mobile")
    fun getNews(): Call<JsonObject>
}