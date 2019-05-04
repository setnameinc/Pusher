package com.setname.pusher.mvp.retfrofit

import com.setname.pusher.mvp.retfrofit.api.APIPushover
import com.setname.pusher.mvp.retfrofit.models.PostModel
import kotlinx.coroutines.Deferred
import retrofit2.Response

class PushoverClient {

    private val DEVELOPER_KEY = "aoek1vexqfquerdnxgfr22oqv816cz"
    private val CLIENT_KEY = "ututjpe39tswiptnxbkhozj3yuu588"

    private val apiService by lazy {

        APIPushover.retrofit

    }

    fun postMessageUsingRetrofit(msg:String): Deferred<Response<PostModel>> =

        apiService.postMessageAsync(PostModel(DEVELOPER_KEY, CLIENT_KEY, msg))

}