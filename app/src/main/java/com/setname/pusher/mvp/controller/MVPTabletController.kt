package com.setname.pusher.mvp.controller

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity(), InteractionsMainActivity {

    private val mvpTabletController = MVPTabletController(this)

    fun setMessage(str: String){

        mvpTabletController.setMessage(str)

    }
}

class MVPTabletController(private val interactionsMainActivity: InteractionsMainActivity) {

    private val tabletPresenter = TabletPresenter(this)

    fun setMessage(str:String){

        tabletPresenter.setTablePresenter()
        tabletPresenter.setMessage(str)

    }

}

class TabletPresenter(private val tableController: MVPTabletController) {

    private val messageListPresenter = MessageListPresenter()

    fun setMessage(str:String){

        messageListPresenter.setMessage(str)

    }

    fun completeSetMessage(){

        println("complete")

    }

    fun setTablePresenter() {

        messageListPresenter.setFragment(this)

    }

}

class MessageListPresenter {

    fun setMessage(str:String){

        fragment.setMessage(str)

    }

    fun setFragment(tabletPresenter:TabletPresenter){

        fragment = FragmentMessages()
        fragment.setTabletPresenter(tabletPresenter)

    }

    private lateinit var fragment:FragmentMessages

}

class FragmentMessages:Fragment() {

    fun setTabletPresenter(tabletPresenter:TabletPresenter) {
        this.tabletPresenter = tabletPresenter
    }

    private lateinit var tabletPresenter:TabletPresenter

    fun setMessage(str: String){
        println(str)
        tabletPresenter.completeSetMessage()
    }

}

interface InteractionsMainActivity{

}
interface InteractionsTabletPresenter{
    fun setTabletPresenter(tabletPresenter:TabletPresenter)
}