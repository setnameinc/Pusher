package com.setname.pusher.mvp.masterview

import com.setname.pusher.mvp.presenter.MainPresenter
import com.setname.pusher.mvp.room.models.MessagesDatabaseModel
import com.setname.pusher.mvp.views.fragments.main_container.InteractionsWithCreateMessage
import com.setname.pusher.mvp.views.main.InteractionsWithMainActivity

class MasterView(private val mainActivity: InteractionsWithMainActivity, private val interactionsWithCreateMessage: InteractionsWithCreateMessage) {

    private val presenter: MainPresenter = MainPresenter(this)

    fun setFragmentMessages(list:List<MessagesDatabaseModel>){

        mainActivity.setFragmentMessages(list)

    }

    fun startSentMessageReceiver(model: MessagesDatabaseModel){

        mainActivity.startSentMessageReceiver(model)

    }

}