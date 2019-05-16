package com.setname.pusher.mvp.views.fragments.create

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.setname.pusher.R
import com.setname.pusher.mvp.presenters.main.TabletPresenter
import com.setname.pusher.mvp.room.models.MessageMainModel
import com.setname.pusher.mvp.room.models.MessagesDatabaseModel
import com.setname.pusher.mvp.utils.context.AppContext
import com.setname.pusher.mvp.views.qrreader.DecoderActivity
import kotlinx.android.synthetic.main.fragment_create_message.*
import kotlinx.android.synthetic.main.fragment_create_message.view.*
import java.util.*

class CreateMessageFragment : Fragment() {

    private val calendar = Calendar.getInstance()

    fun setTabletPresenter(tabletPresenter: TabletPresenter) {
        this.tabletPresenter = tabletPresenter
    }

    private lateinit var tabletPresenter: TabletPresenter

    private lateinit var createView: View

    private var DECODER_CODE = AppContext.applicationContext().resources.getInteger(R.integer.DECODER_CODE)

    init {
        calendar.time = Date()
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + 5)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        createView = inflater.inflate(R.layout.fragment_create_message, container, false)

        return createView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        createView.activity_create_message_message_image_view.setOnClickListener {

            startActivityForResult(Intent(context, DecoderActivity::class.java), DECODER_CODE)

        }

        updateDateView()



        setCalendarImageClickListener()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        when (requestCode) {

            DECODER_CODE -> {

                val text = data?.getStringExtra("text")
                createView.activity_create_message_message_edit_text.setText(text)

                Log.i("CreateMessageFragment", "${text}")

            }

        }
    }

    private fun setCalendarImageClickListener() {

        createView.activity_create_message_message_calendar_view.setOnClickListener {

            val timeListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->

                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                calendar.set(Calendar.MINUTE, minute)

                updateDateView()

            }

            val dateListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                TimePickerDialog(
                    context!!, timeListener,
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE), true
                ).show()

            }

            val dateDialog = DatePickerDialog(
                context, dateListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )

            dateDialog.datePicker.minDate = System.currentTimeMillis() - 1000
            dateDialog.show()

        }

    }

    fun createMessage() {

        if (activity_create_message_message_edit_text.text.toString().isNotEmpty()) {

            sentModelMessagesToDB(
                MessagesDatabaseModel(
                    calendar.timeInMillis,
                    MessageMainModel(activity_create_message_message_edit_text.text.toString()),
                    false
                )
            )

        }

    }

    private fun updateDateView() {

        activity_create_message_message_time.text = DateFormat.format("d MMMM", Date(calendar.timeInMillis))

    }

    fun changeFragment() {

        tabletPresenter.changeFragment(this)

    }

    private fun sentModelMessagesToDB(model: MessagesDatabaseModel) {

        tabletPresenter.sentDataToDB(model)

        hideSoftKeyboard()

        finishFragment()

    }

    private fun finishFragment(){

        fragmentManager?.beginTransaction()?.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right)
            ?.remove(this)?.commit()

    }

    private fun hideSoftKeyboard() {

        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(getView()?.getWindowToken(), 0)

    }

}

