package com.oskarconislla.hackernewsapp.view

import android.content.Context
import android.content.SharedPreferences

import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.oskarconislla.hackernewsapp.R
import com.oskarconislla.hackernewsapp.customclasses.SwipeGesture
import com.oskarconislla.hackernewsapp.model.entities.New
import com.oskarconislla.hackernewsapp.model.entities.room.*
import com.oskarconislla.hackernewsapp.viewmodel.NewViewModel
import kotlinx.coroutines.runBlocking



class MainActivity : AppCompatActivity() {

    private var newViewModel: NewViewModel? = null
    private var newDao: NewDao? = null
    private var deletedDao: DeletedDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        setupBindings(savedInstanceState)
       val pullToRefresh: SwipeRefreshLayout = findViewById(R.id.pullToRefresh);
        val rvNews: RecyclerView = findViewById(R.id.rvNews);

         pullToRefresh.setOnRefreshListener {
            newViewModel?.callNews(checkConnectivity())
            Toast.makeText(applicationContext, "The news have update successfully!", Toast.LENGTH_SHORT).show()
            pullToRefresh.setRefreshing(false)

        }
        val swipeGesture = object : SwipeGesture(this){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                when(direction){

                    ItemTouchHelper.LEFT -> {
                        deletedDao?.insertOne(Deleted(newViewModel?.getNewAt(viewHolder.bindingAdapterPosition)?.objectID.toString()))
                        newViewModel?.deleteItemAt(viewHolder.bindingAdapterPosition)

                    }

                }

            }
        }
        val touchHelper = ItemTouchHelper(swipeGesture)
        touchHelper.attachToRecyclerView(rvNews)

    }

    fun setupBindings(savedInstanceState: Bundle?){
        val activityMainBinding: com.oskarconislla.hackernewsapp.databinding.ActivityMainBinding
                = DataBindingUtil.setContentView(this, R.layout.activity_main)

        newViewModel = ViewModelProviders.of(this).get(NewViewModel::class.java)

        activityMainBinding.setModel(newViewModel)
        val app = applicationContext as NewApp
        newDao = app.room.newDao()
        deletedDao = app.deletedDB.deletedDao()

    }

    override fun onStart() {
        super.onStart()

    }

    override fun onResume() {
        super.onResume()
        setupListUpdate()
    }

    override fun onStop() {
        super.onStop()
     }



    fun setupListUpdate(){
        val connected = checkConnectivity()
        newViewModel?.getNews()?.observe(this, Observer {
                news: List<New> ->
             newViewModel!!.setNewsInRecyclerAdapter(news)
            if(connected){
                var newsRoom: List<NewRoom> = listOf<NewRoom>()
                newDao?.deleteAll()
                news.forEach { new ->

                    newDao?.insertOne(NewRoom(
                        new.objectID,
                        new.story_title,
                        new.author,
                        new.created_at_date,
                        new.story_url
                    ))
                }
                var newsRoom2: List<NewRoom>? = runBlocking {
                    newDao?.getAll()
                }

            }
        })

        newViewModel?.callNews(connected)



    }

    private fun checkConnectivity() : Boolean{
        val cm=getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        val isConnected = activeNetwork !=null && activeNetwork.isConnectedOrConnecting
        return isConnected


    }

}