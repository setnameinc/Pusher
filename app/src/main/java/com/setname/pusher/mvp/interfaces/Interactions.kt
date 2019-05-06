package com.setname.pusher.mvp.interfaces

import android.support.v4.app.Fragment
import com.setname.pusher.mvp.presenter.main.TabletPresenter
import com.setname.pusher.mvp.utils.receivers.SentMessageReceiver

interface InteractionsMainActivity{

    fun changeFragment(fragment: Fragment)
    fun setClosedCreateViewClickListener()
    fun setOpenCreateViewClickListener()
    fun sentMessageReceiver(time: Long, messageReceiver: SentMessageReceiver)

}
interface InteractionsTabletPresenter{

    fun setTabletPresenter(tabletPresenter: TabletPresenter)

}