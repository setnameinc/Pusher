package com.setname.pusher.mvp.presenter.receivers

import com.setname.pusher.mvp.presenter.main.TabletPresenter
import com.setname.pusher.mvp.room.models.MessagesDatabaseModel
import com.setname.pusher.mvp.utils.receivers.SentMessageReceiver

class SentMessagesPresenter {

    private var listOfStartedSentMessage = mutableListOf<SentMessageReceiver>()


    fun removeSentMessageReceiver(time:Long){

        //change status in DB

    }

    fun setExtraDataForMessageReceiver(sentMessageReceiver: SentMessageReceiver){

        listOfStartedSentMessage.add(sentMessageReceiver)

    }

    fun addListSentMessageReceiver(list: List<MessagesDatabaseModel>, tabletPresenter: TabletPresenter){

        for (message in list){

            addSentMessageReceiver(message.time, tabletPresenter)

        }

    }

    fun addSentMessageReceiver(time:Long, tabletPresenter: TabletPresenter){

        val messageReceiver = SentMessageReceiver()
        messageReceiver.setTabletPresenter(tabletPresenter)
        messageReceiver.setTimeForSentMessage(time)

    }

}