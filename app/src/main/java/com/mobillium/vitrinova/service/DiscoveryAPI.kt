package com.mobillium.vitrinova.service

import com.google.gson.JsonArray
import io.reactivex.Single
import retrofit2.http.GET

interface DiscoveryAPI {


    @GET("api/v2/discover")
    fun getDiscovery(): Single<JsonArray>
}