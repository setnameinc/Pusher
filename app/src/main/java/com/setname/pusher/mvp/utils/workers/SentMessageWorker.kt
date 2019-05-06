package com.setname.pusher.mvp.utils.workers

import android.util.Log
import androidx.work.Worker
import com.setname.pusher.mvp.presenter.main.TabletPresenter
import android.support.v4.app.NotificationCompat
import android.content.Context.NOTIFICATION_SERVICE
import android.support.v4.content.ContextCompat.getSystemService
import android.app.NotificationManager
import android.content.Context
import com.setname.pusher.R
import kotlin.random.Random
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.graphics.Color


class SentMessageWorker : Worker() {

    override fun doWork(): WorkerResult {

        /*if (isStopped){

            return WorkerResult.FAILURE

        } else {*/

            val notificationManager =
                applicationContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

            val notification = NotificationCompat.Builder(applicationContext, "default")
                .setSmallIcon(R.mipmap.ic_pusher)//not working
                .setContentTitle("Pusher")
                .setContentText("Message has been sent!")
                .setAutoCancel(true)

            notificationManager.notify(1, notification.build())

        /*}*/

        return WorkerResult.SUCCESS
    }

}