package com.setname.pusher.mvp.presenters.main

import android.support.v4.app.Fragment
import com.setname.pusher.mvp.controllers.fragments.create.CreateMessageController
import com.setname.pusher.mvp.controllers.fragments.display.DisplayMessagesController
import com.setname.pusher.mvp.presenters.worker.SentMessageWorkerPresenter
import com.setname.pusher.mvp.room.models.MessagesDatabaseModel

class TabletPresenter(private val mvpTabletPresenter: MVPTabletPresenter) {

    private val messageListPresenter = DisplayMessagesController()
    private val createMessagePresenter = CreateMessageController()
    private val sendMessagePresenter = SentMessageWorkerPresenter()

    fun changeFragment(fragment:Fragment){

        mvpTabletPresenter.changeFragment(fragment)

    }

    fun setTablePresenter() {

        messageListPresenter.setFragment(this)

    }

    fun setDataForRV(list: List<MessagesDatabaseModel>){

        messageListPresenter.setDataForRV(list)

    }

    fun createMessage(){

        createMessagePresenter.createMessage()

    }

    fun addCreateMessageView(){

        createMessagePresenter.setFragment(this)

    }

    fun sentDataToDB(model: MessagesDatabaseModel){

        mvpTabletPresenter.sentDataToDB(model)

    }

    fun updateDisplayMessage(){

        mvpTabletPresenter.loadMessagesList()

    }

    fun startSentMessageWorker(){

        sendMessagePresenter.initSentMessageWorkerPresenter(this)
        sendMessagePresenter.addAllWorkers(mvpTabletPresenter.loadMessagesForStartServices())

    }

    fun setWorkers(list:List<Long>){

        mvpTabletPresenter.setWorkers(list)

    }


}