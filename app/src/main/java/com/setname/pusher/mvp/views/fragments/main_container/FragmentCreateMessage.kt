package com.setname.pusher.mvp.views.fragments.main_container

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.setname.pusher.R
import com.setname.pusher.mvp.views.qrreader.DecoderActivity
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragment_create_message.view.*

class FragmentCreateMessage : Fragment() {

    private lateinit var createView: View

    private var DECODER_CODE = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        DECODER_CODE = resources.getInteger(R.integer.DECODER_CODE)
        createView = inflater.inflate(R.layout.fragment_create_message, container, false)

        return createView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        createView.activity_create_message_message_image_view.setOnClickListener {

            startActivityForResult(Intent(context, DecoderActivity::class.java), DECODER_CODE)

        }

        createView.activity_create_message_floating_button.apply {

            setOnClickListener {

                fragmentManager!!.beginTransaction().remove(this@FragmentCreateMessage).commit()

            }

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        when (requestCode) {

            DECODER_CODE -> {

                val text = data?.getStringExtra("text")
                createView.activity_create_message_message_edit_text.setText(text)

                Log.i("fsdfasdf", "${text}")

            }

        }
    }

}

interface InteractionsWithCreateMessage{



}


