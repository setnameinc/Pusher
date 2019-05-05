package com.setname.pusher.mvp.presenter.fragment.create

import com.setname.pusher.mvp.presenter.main.TabletPresenter
import com.setname.pusher.mvp.views.fragments.main_container.CreateMessageFragment

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