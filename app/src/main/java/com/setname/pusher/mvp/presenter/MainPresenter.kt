package com.setname.pusher.mvp.presenter

import com.setname.pusher.mvp.retfrofit.PushoverClient
import com.setname.pusher.mvp.room.models.MessageMainModel
import com.setname.pusher.mvp.room.models.MessagesDatabaseModel
import com.setname.pusher.mvp.utils.context.AppContext
import com.setname.pusher.mvp.utils.dbinteractions.InteractionsWithDatabase
import com.setname.pusher.mvp.views.main.InteractionsWithMainView
import java.util.*
import java.util.logging.Logger

class MainPresenter(private val mainView: InteractionsWithMainView) {


    private val logger by lazy {

        Logger.getLogger("MainActivityLog")

    }

    private val pusherAPIService by lazy {

        PushoverClient()

    }

    private val interactionsWithDB by lazy {

        InteractionsWithDatabase(AppContext.applicationContext())

    }

    private val currentTime by lazy {

        Calendar.getInstance().time.time

    }

    private val context by lazy {
        AppContext.applicationContext()
    }

    init {

        setSetsOfBroadcastReceivers()

        mainView.setFragmentMessages(interactionsWithDB.getAll())

        /*insertTest()*/

    }

    fun setSetsOfBroadcastReceivers(){

        val list = interactionsWithDB.getAllByTime(currentTime)

         if (list.isNotEmpty()){

             list.forEach { mainView.startSentMessageReceiver(it) }

         }

    }

    fun insertTest(){

        var calendar = Calendar.getInstance()
        calendar.time = Date()
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + 5)

        interactionsWithDB.insertMessage(MessagesDatabaseModel(time = calendar.timeInMillis, main = MessageMainModel("message")))

    }

    fun insert(model: MessagesDatabaseModel){

        interactionsWithDB.insertMessage(model)

    }

}