package com.setname.pusher.mvp.controllers.fragments.create

import com.setname.pusher.mvp.presenters.main.TabletPresenter
import com.setname.pusher.mvp.views.fragments.create.CreateMessageFragment

class CreateMessageController{

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