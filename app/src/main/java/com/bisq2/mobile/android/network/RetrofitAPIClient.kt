package com.bisq2.mobile.android.network

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor

object RetrofitAPIClient {
    const val BASE_URL = "https://www.google.com"
    private var retrofit: Retrofit? = null
    fun getRetrofitClient(mContext: Context?): Retrofit? {
        if (retrofit == null) {
            val oktHttpClient = OkHttpClient.Builder()
                .addInterceptor(NetworkConnectivityInterceptor(mContext!!))
            // Adding NetworkConnectionInterceptor with okHttpClientBuilder.
            val logging = HttpLoggingInterceptor()
            // set your desired log level
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            oktHttpClient.addInterceptor(logging)
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(oktHttpClient.build())
                .build()
        }
        return retrofit
    }
}