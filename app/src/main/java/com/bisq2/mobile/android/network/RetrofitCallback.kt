package com.bisq2.mobile.android.network

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.bisq2.mobile.android.R
import com.google.gson.JsonSyntaxException
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


abstract class RetrofitCallback<T> : Callback<T> {
    private var ProgressBar: ProgressBar? = null
    private var context: Context
    private var validateResponse = true

    constructor(c: Context) {
        context = c
    }

    constructor(c: Context, dialog: ProgressBar?) {
        ProgressBar = dialog
        context = c
    }

    constructor(context: Context, ProgressBar: ProgressBar?, validateResponse: Boolean) {
        this.ProgressBar = ProgressBar
        this.context = context
        this.validateResponse = validateResponse
    }

    constructor(context: Context, validateResponse: Boolean) {
        this.context = context
        this.validateResponse = validateResponse
    }

    abstract fun onSuccess(arg0: T)
    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (!(context as Activity).isFinishing && ProgressBar != null && ProgressBar!!.isShown) {
            if (ProgressBar != null && ProgressBar!!.isShown) {
                ProgressBar!!.visibility = View.GONE
            }
        }
        if (response.isSuccessful() && response.code() === 200) {
            response.body()?.let { onSuccess(it) }
        } else {
            Toast.makeText(
                context, context.getString(R.string.something_wrong),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onFailure(call: Call<T>, error: Throwable) {
        if (!validateResponse) return
        val errorMsg: String?
        error.printStackTrace()
        errorMsg = when (error) {
            is SocketTimeoutException -> {
                context.getString(R.string.connection_timeout)
            }
            is UnknownHostException -> {
                context.getString(R.string.no_internet)
            }
            is ConnectException -> {
                context.getString(R.string.server_not_responding)
            }
            is JSONException, is JsonSyntaxException -> {
                context.getString(R.string.parse_error)
            }
            is IOException -> {
                error.message
            }
            else -> {
                context.getString(R.string.something_wrong)
            }
        }
        if (ProgressBar != null && ProgressBar!!.isShown) {
            if (ProgressBar != null && ProgressBar!!.isShown) {
                ProgressBar!!.visibility = View.GONE
            }
        }
        Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show()
    }
}