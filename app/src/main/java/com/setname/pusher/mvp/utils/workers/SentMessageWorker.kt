package com.setname.pusher.mvp.utils.workers

import androidx.work.Worker
import android.support.v4.app.NotificationCompat
import android.content.Context.NOTIFICATION_SERVICE
import android.app.NotificationManager
import com.setname.pusher.R


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