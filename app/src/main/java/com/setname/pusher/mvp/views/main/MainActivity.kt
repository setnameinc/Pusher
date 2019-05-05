package com.setname.pusher.mvp.views.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.setname.pusher.R
import com.setname.pusher.mvp.controller.MVPTabletController
import com.setname.pusher.mvp.interfaces.InteractionsMainActivity
import com.setname.pusher.mvp.views.fragments.main_container.CreateMessageFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), InteractionsMainActivity {

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
            setOnClickListener { mvpTabletController.addCreateMessageView() }
            background = ContextCompat.getDrawable(context, R.mipmap.ic_confirm)
            isClickable = true

        }
    }

    private fun setButtonToDisplayMessagesFragment() {

        activity_main_button_right_bottom.apply {

            isClickable = false
            setOnClickListener { mvpTabletController.createMessage() }
            background = ContextCompat.getDrawable(context, R.mipmap.ic_add)
            isClickable = true

        }

    }

    private val mvpTabletController = MVPTabletController(this)

    /*
     override fun startSentMessageReceiver(model: MessagesDatabaseModel) {

         val ALARM_CODE = resources.getInteger(R.integer.ALARM_CODE)
         val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

         var calendar = Calendar.getInstance()
         calendar.time = Date()
         calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND))

         var intent = Intent(this, SentMessageReceiver::class.java)
         intent.putExtra("model", model)

         val pendingIntent = PendingIntent.getBroadcast(this, ALARM_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT)
         alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)

         Log.i("MainActivityLog", "${intent.getParcelableExtra<MessagesDatabaseModel>("model")}")

     }

     private var ALARM_CODE = 0*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mvpTabletController.setMessagesList()

        setButtonToCreateMessageFragment()

/*
        ALARM_CODE = resources.getInteger(R.integer.ALARM_CODE)
*/

    }

}
