package com.setname.pusher.mvp.room.converters

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.setname.pusher.mvp.room.models.MessageMainModel

class ConverterMessageModelForDBFormat {

    companion object {

        @TypeConverter
        @JvmStatic
        fun fromString(value: String): MessageMainModel {

            val list = object : TypeToken<MessageMainModel>() {}.type

            return Gson().fromJson(value, list)

        }

        @TypeConverter
        @JvmStatic
        fun fromArrayList(list: MessageMainModel): String {
            val gson = Gson()
            return gson.toJson(list)
        }


    }

}