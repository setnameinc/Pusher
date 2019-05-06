package com.setname.pusher.mvp.presenter.fragment.display

import com.setname.pusher.mvp.presenter.main.TabletPresenter
import com.setname.pusher.mvp.room.models.MessagesDatabaseModel
import com.setname.pusher.mvp.views.fragments.main_container.DisplayMessagesFragment

class DisplayMessagesPresenter {

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