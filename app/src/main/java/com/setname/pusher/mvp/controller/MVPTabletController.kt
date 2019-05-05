package com.setname.pusher.mvp.controller

import android.support.v4.app.Fragment
import com.setname.pusher.mvp.interfaces.InteractionsMainActivity
import com.setname.pusher.mvp.presenter.main.TabletPresenter
import com.setname.pusher.mvp.retfrofit.PushoverClient
import com.setname.pusher.mvp.room.models.MessagesDatabaseModel
import com.setname.pusher.mvp.utils.context.AppContext
import com.setname.pusher.mvp.utils.dbinteractions.InteractionsWithDatabase
import java.util.*
import java.util.logging.Logger

class MVPTabletController(private val interactionsMainActivity: InteractionsMainActivity) {

    private val logger by lazy {

        Logger.getLogger("MVPTabletController")

    }

    private val pusherAPIService by lazy {

        PushoverClient()

    }

    private val interactionsWithDB by lazy {

        InteractionsWithDatabase(context)

    }

    private val currentTime by lazy {

        Calendar.getInstance().time.time

    }

    private val context by lazy {
        AppContext.applicationContext()
    }

    private val tabletPresenter = TabletPresenter(this)

    fun changeFragment(fragment: Fragment){

        interactionsMainActivity.changeFragment(fragment)

    }

    fun setMessagesList() {

        fun setDataForRV(list: List<MessagesDatabaseModel>) {

            tabletPresenter.setDataForRV(list)

        }

        tabletPresenter.setTablePresenter()

        setDataForRV(interactionsWithDB.getAll())

    }

    fun createMessage(){

        tabletPresenter.createMessage()

    }

    fun addCreateMessageView(){

        tabletPresenter.addCreateMessageView()

    }

    fun setOpenCreateViewClickListener(){

        interactionsMainActivity.setOpenCreateViewClickListener()

    }

    fun sentDataToDB(model: MessagesDatabaseModel){

        interactionsWithDB.insertMessage(model)

    }

    init {

        /*fun setSetsOfBroadcastReceivers(){

            val list = interactionsWithDB.getAllByTime(currentTime)

            if (list.isNotEmpty()){

                list.forEach { masterView.startSentMessageReceiver(it) }

            }

        }


        setSetsOfBroadcastReceivers()*/

    }

}