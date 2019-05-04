package com.setname.pusher.mvp.room.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.setname.pusher.mvp.room.models.MessagesDatabaseModel

@Dao
interface PusherDAO {

    @Insert
    fun insert(messagesDatabaseModel: MessagesDatabaseModel)

    @Query("SELECT * FROM messages WHERE time <= :time")
    fun getAllByTime(time:Long): List<MessagesDatabaseModel>

    @Query("SELECT * FROM messages")
    fun getAll(): List<MessagesDatabaseModel>

}