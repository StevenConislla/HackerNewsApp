package com.oskarconislla.hackernewsapp.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.AndroidViewModel
import com.oskarconislla.hackernewsapp.R
import com.oskarconislla.hackernewsapp.model.entities.New
import com.oskarconislla.hackernewsapp.model.entities.NewObservable
import com.oskarconislla.hackernewsapp.view.adapters.RecyclerNewsAdapter

class NewViewModel(application : Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private var newObservable: NewObservable = NewObservable(context)
    private var recyclerNewsAdapter: RecyclerNewsAdapter? = RecyclerNewsAdapter(this, R.layout.item_new)
    fun callNews(connected : Boolean){
        newObservable.callNews(connected)
    }

    fun getNews() : MutableLiveData<List<New>> {
        return newObservable.getNews()
    }

     fun setNewsInRecyclerAdapter(news: List<New>){
       recyclerNewsAdapter?.setCounponsList(news)
         recyclerNewsAdapter?.notifyDataSetChanged()

    }


    fun getRecyclerNewsAdapter(): RecyclerNewsAdapter?{
        return recyclerNewsAdapter
    }

    fun deleteItemAt(position: Int){
        newObservable.deleteItemAt(position)
        recyclerNewsAdapter?.notifyDataSetChanged()
    }

    fun getNewAt(position: Int): New?{
        var coupons: List<New>? = newObservable.getNews().value
        return coupons?.get(position)
    }



}