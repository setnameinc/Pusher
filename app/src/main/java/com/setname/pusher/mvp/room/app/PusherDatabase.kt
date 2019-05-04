package com.setname.pusher.mvp.room.app

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.setname.pusher.mvp.room.converters.ConverterMessageModelForDBFormat
import com.setname.pusher.mvp.room.dao.PusherDAO
import com.setname.pusher.mvp.room.models.MessagesDatabaseModel

@Database(entities = arrayOf(MessagesDatabaseModel::class), version = 1, exportSchema = false)
@TypeConverters(ConverterMessageModelForDBFormat::class)
abstract class PusherDatabase : RoomDatabase() {

    abstract fun pusherDAO(): PusherDAO

    companion object {

        @Volatile
        private var INSTANCE: PusherDatabase? = null

        fun getInstance(context: Context): PusherDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                PusherDatabase::class.java, "Sample.db"
            ).allowMainThreadQueries().build()
    }

}