package com.bisq2.mobile.android.controllers

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

abstract class GoogleSearchService {
    @GET("/2.0/?method=search")
    abstract fun search(@Query("q") q: String): Call<String>
}

