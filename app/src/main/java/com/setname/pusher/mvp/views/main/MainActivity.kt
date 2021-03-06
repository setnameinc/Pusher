package com.setname.pusher.mvp.views.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import androidx.work.*
import com.setname.pusher.R
import com.setname.pusher.mvp.controllers.fragments.main.MVPTabletController
import com.setname.pusher.mvp.customviews.customsnackbar.CustomSnackbarWithTimer
import com.setname.pusher.mvp.interfaces.InteractionsMainActivity
import com.setname.pusher.mvp.room.models.MessagesDatabaseModel
import com.setname.pusher.mvp.utils.workers.SentMessageWorker
import com.setname.pusher.mvp.views.fragments.create.CreateMessageFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(), InteractionsMainActivity {

    private val internetConnection = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    override fun setWork(model: MessagesDatabaseModel) {

        val time = model.time

        val timeToPush = if (time - System.currentTimeMillis() >= 0) time - System.currentTimeMillis() else 0

        val work =
            OneTimeWorkRequest.Builder(SentMessageWorker::class.java)
                .setConstraints(internetConnection)
                .setInitialDelay(timeToPush, TimeUnit.MILLISECONDS).build()

        setObserver(work.id, time)

        workManager.enqueue(work)

    }

    override fun setWorkers(list: List<Long>) {

        val listOfOneTimeWorkRequest = mutableListOf<OneTimeWorkRequest>()

        val internetConnection = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        for (time in list) {

            val timeToPush = if (time - System.currentTimeMillis() >= 0) time - System.currentTimeMillis() else 0

            val work =
                OneTimeWorkRequest.Builder(SentMessageWorker::class.java)
                    .setInitialDelay(timeToPush, TimeUnit.MILLISECONDS)
                    .setConstraints(internetConnection)
                    .build()

            setObserver(work.id, time)

            listOfOneTimeWorkRequest.add(work)

        }

        workManager.enqueue(listOfOneTimeWorkRequest)

    }

    private fun setObserver(id: UUID, time: Long) {

        workManager.getStatusById(id).observe(this, android.arch.lifecycle.Observer {

            when (it?.state) {

                State.FAILED -> {

                    Log.i("MainActivityTag", "failed: time = ${time}")

                }

                State.SUCCEEDED -> {

                    mvpTabletController.changeStatus(time)

                }

                State.CANCELLED -> {


                }

                else -> {


                }
            }

        })

    }

    override fun setOpenCreateViewClickListener() {
        setButtonToCreateMessageFragment()
    }

    override fun setClosedCreateViewClickListener() {
        setButtonToDisplayMessagesFragment()
    }

    override fun changeFragment(fragment: Fragment) {

        when (fragment) {

            is CreateMessageFragment -> {
                supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right)
                    .add(R.id.main_container, fragment)
                    .commit()
                setButtonToDisplayMessagesFragment()
            }
            else -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_container, fragment)
                    .commit()
                setButtonToCreateMessageFragment()
            }

        }


    }

    private fun setButtonToCreateMessageFragment() {

        activity_main_button_right_bottom.apply {

            isClickable = false
            setImageResource(R.mipmap.ic_add)
            setOnClickListener { mvpTabletController.addCreateMessageView() }
            /*background = ContextCompat.getDrawable(context, R.mipmap.ic_add)*/
            isClickable = true

        }
    }

    private fun setButtonToDisplayMessagesFragment() {

        activity_main_button_right_bottom.apply {

            isClickable = false
            setOnClickListener { mvpTabletController.createMessage() }
            setImageResource(R.mipmap.ic_confirm)
            /*background = ContextCompat.getDrawable(context, R.mipmap.ic_confirm)*/
            isClickable = true

        }

    }

    private val mvpTabletController = MVPTabletController(this)

    private val workManager = WorkManager.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mvpTabletController.setMessagesList()

        setButtonToCreateMessageFragment()

        mvpTabletController.startSentMessageWorker()

        /*CustomSnackbarWithTimer.make(activity_main_parent_layout).apply {
            setAction("The message was deleted", View.OnClickListener {

                Log.i("MainArt", "message")

            })
            duration = 5000
        }.show()*/

    }

}
