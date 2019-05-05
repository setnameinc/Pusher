package com.setname.pusher.mvp.interfaces

import android.support.v4.app.Fragment
import com.setname.pusher.mvp.presenter.main.TabletPresenter

interface InteractionsMainActivity{

    fun changeFragment(fragment: Fragment)
    fun setClosedCreateViewClickListener()
    fun setOpenCreateViewClickListener()

}
interface InteractionsTabletPresenter{

    fun setTabletPresenter(tabletPresenter: TabletPresenter)

}