package com.mobillium.vitrinova.service

import com.google.gson.JsonArray
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class DiscoveryAPIService {


    private val baseURL = "https://www.vitrinova.com/"


    private val api = Retrofit.Builder()
        .baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(DiscoveryAPI::class.java)


    fun getDiscovery(): Single<JsonArray> {

        return api.getDiscovery()

    }

}