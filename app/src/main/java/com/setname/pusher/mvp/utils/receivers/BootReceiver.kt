package com.setname.pusher.mvp.utils.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.setname.pusher.mvp.interfaces.BootReceiverInterface

class BootReceiver : BroadcastReceiver(), BootReceiverInterface {

    override fun getAll() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    var mContext: Context? = null
    private val BOOT_ACTION = "android.intent.action.BOOT_COMPLETED"

    override fun onReceive(context: Context?, intent: Intent?) {
        mContext = context
        val action = intent?.action
        if (action.equals(BOOT_ACTION, true)) {

            //check time from db

        }
    }
}