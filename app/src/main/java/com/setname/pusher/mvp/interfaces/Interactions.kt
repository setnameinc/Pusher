package com.setname.pusher.mvp.interfaces

import android.support.v4.app.Fragment
import androidx.work.Worker
import com.setname.pusher.mvp.presenter.main.TabletPresenter
import com.setname.pusher.mvp.room.models.MessagesDatabaseModel

interface InteractionsMainActivity{

    fun changeFragment(fragment: Fragment)
    fun setClosedCreateViewClickListener()
    fun setOpenCreateViewClickListener()
    fun setWorkers(list:List<Long>)
    fun setWork(model: MessagesDatabaseModel)

}
interface InteractionsTabletPresenter{

    fun setTabletPresenter(tabletPresenter: TabletPresenter)

}