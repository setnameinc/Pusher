package com.setname.pusher.mvp.presenters.main

import android.support.v4.app.Fragment
import com.setname.pusher.mvp.presenters.create.CreateMessagePresenter
import com.setname.pusher.mvp.presenters.display.DisplayMessagesPresenter
import com.setname.pusher.mvp.controllers.fragments.main.MVPTabletController
import com.setname.pusher.mvp.presenters.worker.SentMessageWorkerPresenter
import com.setname.pusher.mvp.room.models.MessagesDatabaseModel

class TabletPresenter(private val mvpTabletController: MVPTabletController) {

    private val messageListPresenter = DisplayMessagesPresenter()
    private val createMessagePresenter = CreateMessagePresenter()
    private val sendMessagePresenter = SentMessageWorkerPresenter()

    fun changeFragment(fragment:Fragment){

        mvpTabletController.changeFragment(fragment)

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

        mvpTabletController.sentDataToDB(model)

    }

    fun updateDisplayMessage(){

        mvpTabletController.loadMessagesList()

    }

    fun startSentMessageWorker(){

        sendMessagePresenter.initSentMessageWorkerPresenter(this)
        sendMessagePresenter.addAllWorkers(mvpTabletController.loadMessagesForStartServices())

    }

    fun setWorkers(list:List<Long>){

        mvpTabletController.setWorkers(list)

    }


}