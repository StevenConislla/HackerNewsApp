package com.oskarconislla.hackernewsapp.model.entities

import android.text.format.DateUtils
import com.google.gson.JsonObject
import java.io.Serializable
import java.lang.Exception
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class New(newJson: JsonObject?) : Serializable {
    lateinit var objectID: String
    var story_title: String = ""
    var author: String = ""
    var created_at: String = ""
    var created_at_date: String = ""
    var story_url: String = ""

    init {
        try {
            objectID           = newJson!!.get(OBJECT_ID).asString
            story_title               = newJson?.get(ST0RY_TITLE)?.let { getStoryTitle(it.asString) }.toString()

            author               = newJson!!.get(AUTHOR).asString

            created_at            = getFormatDate(newJson!!.get(CREATED_AT).asString)
            created_at_date    = newJson!!.get(CREATED_AT).asString
            story_url             = newJson!!.get(STORY_URL).asString
        }catch (e: Exception){
            e.printStackTrace()
        }


    }

    companion object {
        private val OBJECT_ID               = "objectID"
        private val ST0RY_TITLE               = "story_title"
        private val AUTHOR               = "author"

        private val CREATED_AT              = "created_at"

        private val STORY_URL              = "story_url"


    }
    private fun getStoryTitle(title:String):String {
        return if (title!=null) {
            title
        }

        else{
           "story_title"
        }
    }
    private fun getFormatDate(dateNew:String):String {


        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        try {
            val parsedDateFormat = format.parse(dateNew)
            val cal = Calendar.getInstance()
            cal.time = parsedDateFormat
            val relativeDate = DateUtils.getRelativeTimeSpanString(System.currentTimeMillis(),parsedDateFormat.time,  DateUtils.MINUTE_IN_MILLIS, DateUtils.FORMAT_ABBREV_RELATIVE)
            return relativeDate.toString()
        } catch (e: ParseException) {
            e.printStackTrace()
            return ""
        }
    }


}