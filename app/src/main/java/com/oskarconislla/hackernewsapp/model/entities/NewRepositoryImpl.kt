package com.oskarconislla.hackernewsapp.model.entities

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonSerializer
import com.oskarconislla.hackernewsapp.model.ApiAdapter
import com.oskarconislla.hackernewsapp.model.entities.room.NewApp
import com.oskarconislla.hackernewsapp.model.entities.room.NewRoom
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.R.array
import android.content.Context.MODE_PRIVATE
import com.oskarconislla.hackernewsapp.model.entities.room.Deleted
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule


class NewRepositoryImpl : NewRepository {

    private var news = MutableLiveData<List<New>>()


    override fun getNews(): MutableLiveData<List<New>> {
        return news
    }

    override fun callNewsAPI(context: Context) {
        var newsList: ArrayList<New>? = ArrayList<New>()
        val apiAdapter = ApiAdapter()
        val apiService = apiAdapter.getClientService()
        val call = apiService.getNews()

        val app = context as NewApp
        var listDeleted : List<Deleted> = app.deletedDB.deletedDao().getAll()



        call.enqueue(object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                 t.message?.let { Log.e("ERROR: ", it) }
                t.stackTrace
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val offersJsonArray = response.body()?.getAsJsonArray("hits")
                 offersJsonArray?.forEach { jsonElement: JsonElement ->
                    var jsonObject = jsonElement.asJsonObject
                    var new = New(jsonObject)
                    if ((listDeleted?.indexOfFirst{ id -> id.objectID== new.objectID} ?: -1) < 0 ){
                        newsList?.add(new)
                     }
                }

                news.value = newsList

            }




        })
    }

    override fun callNewsRoom(context: Context) {
        val app = context as NewApp
        var listaNewsRoom : List<NewRoom> = app.room.newDao().getAll()
        var listaNews: ArrayList<New>? = ArrayList<New>()
        listaNewsRoom.forEach { new ->
            val app = context as NewApp
            var listDeleted : List<Deleted> = app.deletedDB.deletedDao().getAll()

            if ((listDeleted?.indexOfFirst{ id -> id.objectID== new.objectID} ?: -1) < 0 ){
                val objeto = JsonObject()
                objeto.addProperty("objectID", new.objectID)
                objeto.addProperty("story_title", new.story_title)
                objeto.addProperty("author", new.author)
                objeto.addProperty("created_at", new.created_at)
                objeto.addProperty("story_url", new.story_url)
                Log.d("forEachItemLoaded",objeto.toString())
                listaNews?.add(
                    New(
                        objeto
                    )

                )

            }


        }
        Log.d("listacompletada",listaNews?.size.toString())
        news.value = listaNews


    }



    override fun deleteItemAt(position: Int) {
        var newsList: ArrayList<New>? = news.value as ArrayList<New>?
        newsList?.removeAt(position)
        news.value=newsList
    }

}