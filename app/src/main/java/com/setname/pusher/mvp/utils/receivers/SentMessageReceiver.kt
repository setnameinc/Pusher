package com.setname.pusher.mvp.utils.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.setname.pusher.mvp.presenter.main.TabletPresenter
import com.setname.pusher.mvp.retfrofit.PushoverClient
import com.setname.pusher.mvp.room.models.MessagesDatabaseModel
import com.setname.pusher.mvp.utils.context.AppContext
import com.setname.pusher.mvp.utils.dbinteractions.InteractionsWithDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.atomic.AtomicLong

class SentMessageReceiver : BroadcastReceiver() {

    fun setTabletPresenter(tabletPresenter: TabletPresenter) {
        this.tabletPresenter = tabletPresenter
    }

    private lateinit var tabletPresenter: TabletPresenter

    override fun onReceive(context: Context?, intent: Intent?) {

        /*tabletPresenter.changeStatus(System.currentTimeMillis())*/

    }

    fun setTimeForSentMessage(time:Long){

        Log.i("SentMessageReceiver","for invoke time is ${time}")

        tabletPresenter.startSentMessageReceiver(time, this)

    }

}