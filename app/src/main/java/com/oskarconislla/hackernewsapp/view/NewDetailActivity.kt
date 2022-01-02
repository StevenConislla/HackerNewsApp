package com.oskarconislla.hackernewsapp.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.oskarconislla.hackernewsapp.R
import com.oskarconislla.hackernewsapp.model.entities.New
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class NewDetailActivity : AppCompatActivity() {

    private var newSelected: New? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_detail)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
        }
        newSelected = intent.getSerializableExtra("NewSelected") as New

        var wvNew: WebView = findViewById(R.id.wvNew)

        wvNew.getSettings().setJavaScriptEnabled(true)
        wvNew.getSettings().setLoadWithOverviewMode(true)
        wvNew.getSettings().setUseWideViewPort(true)
        wvNew.getSettings().setBuiltInZoomControls(true)
        wvNew.getSettings().setPluginState(WebSettings.PluginState.ON)
        wvNew.getSettings().setDomStorageEnabled(true)


        wvNew.setWebViewClient(WebViewClient())
        wvNew.loadUrl("${newSelected?.story_url}")

        setTitle("Back")


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()

        }
        return super.onOptionsItemSelected(item)
    }
}