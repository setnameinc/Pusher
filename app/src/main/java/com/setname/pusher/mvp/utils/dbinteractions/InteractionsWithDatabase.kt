package com.setname.pusher.mvp.utils.dbinteractions

import android.content.Context
import com.setname.pusher.mvp.room.app.PusherDatabase
import com.setname.pusher.mvp.room.models.MessagesDatabaseModel

class InteractionsWithDatabase(context: Context) {

    private val pusherDAO by lazy {
        PusherDatabase.getInstance(context).pusherDAO()
    }

    fun insertMessage(messagesDatabaseModel: MessagesDatabaseModel) {

        pusherDAO.insert(messagesDatabaseModel = messagesDatabaseModel)

    }

    fun getAllByTime(time:Long) = pusherDAO.getAllByTime(time)

    fun getAll() = pusherDAO.getAll()

    fun getByTime(time: Long) = pusherDAO.getByTime(time)

    fun changeStatus(time: Long) = pusherDAO.changeStatus(time)

}