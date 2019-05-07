package com.setname.pusher.mvp.views.fragments.main_container

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.setname.pusher.R
import com.setname.pusher.mvp.adapters.MessagesAdapter
import com.setname.pusher.mvp.presenter.main.TabletPresenter
import com.setname.pusher.mvp.room.models.MessagesDatabaseModel
import kotlinx.android.synthetic.main.fragment_messages_list.*
import java.util.*

class DisplayMessagesFragment : Fragment() {

    private var list: ArrayList<MessagesDatabaseModel> = arrayListOf<MessagesDatabaseModel>()
    private var messageAdapter = MessagesAdapter(list)

    fun setTabletPresenter(tabletPresenter: TabletPresenter) {
        this.tabletPresenter = tabletPresenter
    }

    private lateinit var tabletPresenter: TabletPresenter

    private lateinit var fragmentMessagesListView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        fragmentMessagesListView = inflater.inflate(R.layout.fragment_messages_list, container, false)

        return fragmentMessagesListView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        fragment_messages_list_recycler_view.apply {

            layoutManager = LinearLayoutManager(context)
            adapter = messageAdapter

        }

        setSwipeRefreshListener()

    }

    private fun setSwipeRefreshListener() {

        fragment_messages_list_swipe_refresh.setOnRefreshListener {

            tabletPresenter.updateDisplayMessage()

            fragment_messages_list_swipe_refresh.isRefreshing = false

        }

    }

    fun setDataForRecyclerView(messagesList: List<MessagesDatabaseModel>) {

        list.clear()
        list.addAll(messagesList)
        messageAdapter.notifyDataSetChanged()

    }

    fun changeFragment() {

        tabletPresenter.changeFragment(this)

    }

    fun updateDisplayMessage() {

        tabletPresenter.updateDisplayMessage()

    }

}
