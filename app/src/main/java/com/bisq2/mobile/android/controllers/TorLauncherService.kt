package com.bisq2.mobile.android.controllers

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
//import bisq.tor.Tor

import java.nio.file.Paths

class TorLauncherService : Service() {
    private val TAG = "TorLauncherService"

    override fun onCreate() {
        Log.i(TAG, "Service onCreate")
    }

    override fun onBind(intent: Intent): IBinder? {
        Log.i(TAG, "Service onBind")
        return null
    }

    override fun onDestroy() {
        Log.i(TAG, "Service onDestroy")
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        Log.i(TAG, "Service onStartCommand begins")

        val pid = android.os.Process.myPid()
        val path = Paths.get("").toAbsolutePath().toString()
        //set default instance, so it can be omitted whenever creating Tor (Server)Sockets
        //This will take some time
        Log.i(TAG, "Current path:$path")
//TODO Move tor functionality from HomeActivity to TorController
//        var tor = Tor.getTor("res/tor", pid)
//        tor.start();

        Log.i(TAG, "Service onStartCommand ends")
        return START_NOT_STICKY
    }
}