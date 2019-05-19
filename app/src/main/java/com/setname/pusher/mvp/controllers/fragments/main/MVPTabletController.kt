package com.setname.pusher.mvp.controllers.fragments.main

import android.support.v4.app.Fragment
import android.util.Log
import com.setname.pusher.mvp.interfaces.InteractionsMainActivity
import com.setname.pusher.mvp.presenters.main.TabletPresenter
import com.setname.pusher.mvp.retfrofit.PushoverClient
import com.setname.pusher.mvp.room.models.MessagesDatabaseModel
import com.setname.pusher.mvp.utils.context.AppContext
import com.setname.pusher.mvp.room.dbinteractions.InteractionsWithDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

    fun changeFragment(fragment: Fragment) {

        interactionsMainActivity.changeFragment(fragment)

    }

    fun loadMessagesForStartServices() = interactionsWithDB.getAllNotPostedPushes()

    fun setWorkers(list: List<Long>) {

        interactionsMainActivity.setWorkers(list)

    }

    fun loadMessagesList() {

        tabletPresenter.setDataForRV(interactionsWithDB.getAll())

    }

    fun setMessagesList() {

        tabletPresenter.setTablePresenter()

    }

    fun createMessage() {

        tabletPresenter.createMessage()

    }

    fun addCreateMessageView() {

        tabletPresenter.addCreateMessageView()

    }

    fun sentDataToDB(model: MessagesDatabaseModel) {

        interactionsWithDB.insertMessage(model)
        interactionsMainActivity.setOpenCreateViewClickListener()
        tabletPresenter.updateDisplayMessage()
        interactionsMainActivity.setWork(model)

    }

    fun changeStatus(time: Long) {

        CoroutineScope(Main).launch {

            val request =
                pusherAPIService.postMessageUsingRetrofit(interactionsWithDB.getById(time).main.message)

            withContext(IO) {

                val response = request.await()

                if (response.isSuccessful) {

                    Log.i("MVP", "complete")

                    interactionsWithDB.changeStatus(time)

                    withContext(Main){
                        tabletPresenter.updateDisplayMessage()
                    }

                } else {

                    Log.i("MVP", "error ${response.errorBody()}")

                }

            }

        }


        //

    }

    fun startSentMessageWorker() {

        tabletPresenter.startSentMessageWorker()

    }

}