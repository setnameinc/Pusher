package com.setname.pusher.mvp.presenter.main

import android.support.v4.app.Fragment
import com.setname.pusher.mvp.controller.MVPTabletController
import com.setname.pusher.mvp.presenter.fragment.create.CreateMessagePresenter
import com.setname.pusher.mvp.presenter.fragment.display.DisplayMessagesPresenter
import com.setname.pusher.mvp.presenter.workers.SentMessageWorkerPresenter
import com.setname.pusher.mvp.room.models.MessagesDatabaseModel
import com.setname.pusher.mvp.utils.workers.SentMessageWorker

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