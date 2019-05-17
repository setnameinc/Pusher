package com.setname.pusher.mvp.views.fragments.display

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.setname.pusher.R
import com.setname.pusher.mvp.adapters.MessagesAdapter
import com.setname.pusher.mvp.presenters.main.TabletPresenter
import com.setname.pusher.mvp.room.models.MessagesDatabaseModel
import com.setname.pusher.mvp.utils.swipe_controller.SwipeController
import com.setname.pusher.mvp.utils.swipe_controller.SwipeControllerActions
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
        attachSwipeListener()

    }

    private fun attachSwipeListener() {

        val callback = SwipeController(object : SwipeControllerActions {

            override fun forcePush(pos: Int) {

                messageAdapter.notifyItemRemoved(pos)
                messageAdapter.notifyItemRangeChanged(pos, messageAdapter.itemCount)

                //TODO(force push fun)

            }

            override fun delete(pos: Int) {

                val model = list[pos]

                //если удалять элементы с одной позиции списка, непонятно откуда появляются старые эелменты

                messageAdapter.deleteByPosition(pos)

                showUndoAlertDialog(pos, model)

                Log.i("DisplayMessagesFrag", "deleted = ${model.time}")

                //TODO(delete fun)

            }
        })

        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(fragment_messages_list_recycler_view)

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

    fun showUndoAlertDialog(position: Int, model:MessagesDatabaseModel) {

        val snackbar = Snackbar
            .make(this.fragmentMessagesListView, "Item was removed from the list.", Snackbar.LENGTH_LONG)
        snackbar.setAction("UNDO") {

            messageAdapter.addToAdapterList(position, model)
            fragment_messages_list_recycler_view.scrollToPosition(position)

            Log.i("DisplayMessagesFrag", "restored = ${model.time}")

        }

        snackbar.setActionTextColor(Color.YELLOW)
        snackbar.show()

    }

    fun deleteMessage() {

        //tabletPresenter.delete...

    }

}
