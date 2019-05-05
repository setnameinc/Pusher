package com.setname.pusher.mvp.views.fragments.main_container

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

class CreateMessageFragment : Fragment() {

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

        /*if (activity_create_message_message_edit_text.text.toString().isNotEmpty()) {*/

            Log.i("CreateMessageFragment", "nice")

            sentModelMessagesToDB(
                MessagesDatabaseModel(
                    System.currentTimeMillis(),
                    MessageMainModel(activity_create_message_message_edit_text.text.toString())
                )
            )

        /*}*/

    }

    fun changeFragment() {

        tabletPresenter.changeFragment(this)

    }

    fun sentModelMessagesToDB(model: MessagesDatabaseModel) {

        Log.i("MainActivityLog", "open")


        tabletPresenter.sentDataToDB(model)
        fragmentManager?.beginTransaction()?.remove(this)?.commit()

    }

}

