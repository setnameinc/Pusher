package com.setname.pusher.mvp.views.fragments.main_container

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.setname.pusher.R
import com.setname.pusher.mvp.presenter.main.TabletPresenter
import com.setname.pusher.mvp.room.models.MessageMainModel
import com.setname.pusher.mvp.room.models.MessagesDatabaseModel
import com.setname.pusher.mvp.utils.context.AppContext
import com.setname.pusher.mvp.views.qrreader.DecoderActivity
import kotlinx.android.synthetic.main.fragment_create_message.*
import kotlinx.android.synthetic.main.fragment_create_message.view.*
import android.view.inputmethod.InputMethodManager.HIDE_IMPLICIT_ONLY
import android.content.Context.INPUT_METHOD_SERVICE
import android.support.v4.content.ContextCompat.getSystemService
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.DatePicker
import java.util.*

class CreateMessageFragment : Fragment() {

    private val myCalendar = Calendar.getInstance()

    fun setTabletPresenter(tabletPresenter: TabletPresenter) {
        this.tabletPresenter = tabletPresenter
    }

    private lateinit var tabletPresenter: TabletPresenter

    private lateinit var createView: View

    private var DECODER_CODE = AppContext.applicationContext().resources.getInteger(R.integer.DECODER_CODE)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        createView = inflater.inflate(R.layout.fragment_create_message, container, false)

        return createView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        createView.activity_create_message_message_image_view.setOnClickListener {

            startActivityForResult(Intent(context, DecoderActivity::class.java), DECODER_CODE)

        }

        createView.activity_create_message_message_calendar_view.setOnClickListener{

            val date = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                myCalendar.set(Calendar.YEAR, year)
                myCalendar.set(Calendar.MONTH, monthOfYear)
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            }
            

        }

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

    fun createMessage() {

        if (activity_create_message_message_edit_text.text.toString().isNotEmpty()) {

        sentModelMessagesToDB(
            MessagesDatabaseModel(
                System.currentTimeMillis()+3000,
                MessageMainModel(activity_create_message_message_edit_text.text.toString()),
                false
            )
        )

        }

    }

    fun changeFragment() {

        tabletPresenter.changeFragment(this)

    }

    private fun sentModelMessagesToDB(model: MessagesDatabaseModel) {

        tabletPresenter.sentDataToDB(model)

        hideSoftKeyboard()

        fragmentManager?.beginTransaction()?.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right)
            ?.remove(this)?.commit()

    }

    fun hideSoftKeyboard() {

        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(getView()?.getWindowToken(), 0);

    }

}

