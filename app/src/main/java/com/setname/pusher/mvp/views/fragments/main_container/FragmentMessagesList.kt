package com.setname.pusher.mvp.views.fragments.main_container

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.setname.pusher.R
import com.setname.pusher.mvp.adapters.MessagesAdapter
import com.setname.pusher.mvp.views.main.ParcelableMessagesList
import kotlinx.android.synthetic.main.fragment_messages_list.*
import kotlinx.android.synthetic.main.fragment_messages_list.view.*

class FragmentMessagesList:Fragment(), InteractionsWithFragmentMessageList{

    override fun getFragmentCreateMessage(): Fragment = fragmentCreateMessage

    private lateinit var fragmentMessagesListView: View
    private val fragmentCreateMessage = FragmentCreateMessage()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        fragmentMessagesListView = inflater.inflate(R.layout.fragment_messages_list, container, false)

        return fragmentMessagesListView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentMessagesListView.fragment_messages_list_floating_button.apply {

            setOnClickListener {

                fragmentManager!!.beginTransaction().add(
                    R.id.main_container,
                    fragmentCreateMessage
                ).addToBackStack(null).commit()

            }

            background = ContextCompat.getDrawable(context, R.mipmap.ic_add)

        }

        fragment_messages_list_recycler_view.apply {

            layoutManager = LinearLayoutManager(context)
            adapter = MessagesAdapter(arguments?.getParcelable<ParcelableMessagesList>("list")!!.list)

        }

    }
}

interface InteractionsWithFragmentMessageList{

    fun getFragmentCreateMessage():Fragment

}
