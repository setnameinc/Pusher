package com.setname.pusher.mvp.presenter.main

import android.support.v4.app.Fragment
import com.setname.pusher.mvp.controller.MVPTabletController
import com.setname.pusher.mvp.presenter.fragment.create.CreateMessagePresenter
import com.setname.pusher.mvp.presenter.fragment.display.DisplayMessagesPresenter
import com.setname.pusher.mvp.presenter.receivers.SentMessagesPresenter
import com.setname.pusher.mvp.room.models.MessagesDatabaseModel
import com.setname.pusher.mvp.utils.receivers.SentMessageReceiver

class TabletPresenter(private val mvpTabletController: MVPTabletController) {

    private val messageListPresenter = DisplayMessagesPresenter()
    private val createMessagePresenter = CreateMessagePresenter()
    private val sendMessagePresenter = SentMessagesPresenter()

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

    fun loadMessageList(){

        mvpTabletController.loadMessagesList()

    }

    fun updateDisplayMessage(){

        mvpTabletController.loadMessagesList()

    }

    fun changeStatus(time: Long){

        mvpTabletController.changeStatus(time)

    }

    fun startSendService(){

        sendMessagePresenter.addListSentMessageReceiver(mvpTabletController.loadMessagesForStartServices(), this)

    }

    fun addSendService(time: Long){

        sendMessagePresenter.addSentMessageReceiver(time, this)

    }


    fun startSentMessageReceiver(time:Long, sentMessageReceiver: SentMessageReceiver){

        mvpTabletController.startSentMessageReceiver(time, sentMessageReceiver)

        sendMessagePresenter.setExtraDataForMessageReceiver(sentMessageReceiver)

    }


}