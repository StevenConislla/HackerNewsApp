package com.oskarconislla.hackernewsapp.model.entities

import android.content.Context

import androidx.databinding.BaseObservable
import androidx.lifecycle.MutableLiveData

class NewObservable(val context: Context) : BaseObservable() {

    private var newRepository: NewRepository = NewRepositoryImpl()
    //Repositorio
    fun callNews(connected : Boolean){
        if (connected){
            newRepository.callNewsAPI(context)
        }
        else{
            newRepository.callNewsRoom(context)
        }

    }

    //ViewModel
    fun getNews() : MutableLiveData<List<New>> {
        return newRepository.getNews()
    }

    fun deleteItemAt(position: Int){
        newRepository.deleteItemAt(position)
    }



}