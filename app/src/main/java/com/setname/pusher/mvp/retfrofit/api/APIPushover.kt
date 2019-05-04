package com.setname.pusher.mvp.retfrofit.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.setname.pusher.mvp.retfrofit.models.PostModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface APIPushover{

    @POST("messages.json")
    fun postMessageAsync(@Body postModel:PostModel): Deferred<Response<PostModel>>

    companion object {

        val retrofit: APIPushover by lazy {

            fun makeRetrofit(): Retrofit = Retrofit.Builder()
                .baseUrl("https://api.pushover.net/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()

            return@lazy makeRetrofit().create(APIPushover::class.java)

        }
    }

}