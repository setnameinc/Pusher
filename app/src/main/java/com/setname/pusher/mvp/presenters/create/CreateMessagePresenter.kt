package com.setname.pusher.mvp.presenters.create

import com.setname.pusher.mvp.presenters.main.TabletPresenter
import com.setname.pusher.mvp.views.fragments.create.CreateMessageFragment

class CreateMessagePresenter{

    fun setFragment(tabletPresenter: TabletPresenter){

        fragment = CreateMessageFragment()
        fragment.setTabletPresenter(tabletPresenter)
        fragment.changeFragment()

    }

    fun createMessage(){

        fragment.createMessage()

    }

    private lateinit var fragment: CreateMessageFragment

}