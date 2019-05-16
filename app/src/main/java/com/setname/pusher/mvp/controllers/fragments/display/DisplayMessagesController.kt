package com.setname.pusher.mvp.controllers.fragments.display

import com.setname.pusher.mvp.presenters.main.TabletPresenter
import com.setname.pusher.mvp.room.models.MessagesDatabaseModel
import com.setname.pusher.mvp.views.fragments.display.DisplayMessagesFragment

class DisplayMessagesController {

    fun setFragment(tabletPresenter: TabletPresenter){

        fragmentDisplay = DisplayMessagesFragment()
        fragmentDisplay.setTabletPresenter(tabletPresenter)
        fragmentDisplay.changeFragment()
        fragmentDisplay.updateDisplayMessage()

    }

    fun setDataForRV(list: List<MessagesDatabaseModel>){

        fragmentDisplay.setDataForRecyclerView(list)

    }

    private lateinit var fragmentDisplay: DisplayMessagesFragment

    fun updateDisplayMessage(){

        fragmentDisplay.updateDisplayMessage()

    }

}