package com.setname.pusher.mvp.utils.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.setname.pusher.mvp.retfrofit.PushoverClient
import com.setname.pusher.mvp.room.models.MessagesDatabaseModel
import com.setname.pusher.mvp.utils.context.AppContext
import com.setname.pusher.mvp.utils.dbinteractions.InteractionsWithDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SentMessageReceiver : BroadcastReceiver() {

    private val pusherAPIService by lazy {

        PushoverClient()

    }

    private val interactionsWithDB by lazy {

        InteractionsWithDatabase(AppContext.applicationContext())

    }

    override fun onReceive(context: Context?, intent: Intent?) {

        /*Log.i("MainActivityLog","model = ${messagesDatabaseModel}")*/

        /*val responce = pusherAPIService.postMessageUsingRetrofit("sdf")

        CoroutineScope(Dispatchers.Main).launch {

            withContext(Dispatchers.Main){

                if (responce.await().isSuccessful){

                    interactionsWithDB.deleteByTime(messagesDatabaseModel!!.time)//

                }

            }

        }*/

    }

}