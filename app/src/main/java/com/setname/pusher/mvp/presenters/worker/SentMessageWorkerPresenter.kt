package com.setname.pusher.mvp.presenters.worker

import com.setname.pusher.mvp.presenters.main.TabletPresenter
import com.setname.pusher.mvp.room.models.MessagesDatabaseModel
class SentMessageWorkerPresenter {

    private lateinit var tabletPresenter: TabletPresenter

    fun initSentMessageWorkerPresenter(mTabletPresenter: TabletPresenter) {

        tabletPresenter = mTabletPresenter

    }

    fun addAllWorkers(list: List<MessagesDatabaseModel>) {

        val listOfTimes = list.map { it.time }

        tabletPresenter.setWorkers(listOfTimes)

    }

    /*fun addSentMessageReceiver(time: Long) {

        messageReceiver.setTimeForSentMessage(time)

    }*/

}