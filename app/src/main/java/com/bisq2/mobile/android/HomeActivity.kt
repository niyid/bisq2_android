package com.bisq2.mobile.android

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.bisq2.mobile.android.controllers.GoogleSearchService
import com.bisq2.mobile.android.controllers.TorLauncherService
import com.bisq2.mobile.android.databinding.ActivityHomeBinding
import com.bisq2.mobile.android.network.RetrofitAPIClient
import com.bisq2.mobile.android.network.RetrofitCallback
import retrofit2.Call


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class HomeActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var binding: ActivityHomeBinding
    private lateinit var fullscreenContentControls: LinearLayout
    private val hideHandler = Handler(Looper.myLooper()!!)

    fun startButtonClick(view: View) {
        intent = Intent(this, TorLauncherService::class.java)
        startService(intent)
    }

    fun navigateToBisqEasy(view: View) {
        //TODO Launch service to prepare Bisq Easy
        startActivity(Intent(this, BisqEasyActivity::class.java))
    }

    fun navigateToDashboardChat(view: View) {
        //TODO Launch service to prepare Join Community
        startActivity(Intent(this, JoinCommunityActivity::class.java))
    }

    fun navigateToTradeApps(view: View) {
        //TODO Getting a hang of how all these retrofit pieces fit together
        val retrofit = RetrofitAPIClient.getRetrofitClient(this);
        val googleSearchService = retrofit?.create(GoogleSearchService::class.java)
        val call = googleSearchService?.search("bisq")

        call?.enqueue(object :
            RetrofitCallback<String?>(this, progressBar) {
            override fun onSuccess(someString: String?) {
                //Your Response
            }
        })

        //TODO Launch service to prepare Trade Apps
        startActivity(Intent(this, TradeAppsActivity::class.java))

    }

    @SuppressLint("InlinedApi")
    private val hidePart2Runnable = Runnable {

    }

    private val showPart2Runnable = Runnable {
        // Delayed display of UI elements
        supportActionBar?.show()
        fullscreenContentControls.visibility = View.VISIBLE
    }
    private var isFullscreen: Boolean = false

    private val hideRunnable = Runnable { hide() }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        isFullscreen = true

        progressBar = findViewById<ProgressBar>(R.id.progressBar)
//        fullscreenContentControls = findViewById<LinearLayout>(R.id.homeLayout)


    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100)
    }

    private fun hide() {
        // Hide UI first
        supportActionBar?.hide()
//        fullscreenContentControls.visibility = View.GONE
        isFullscreen = false

        // Schedule a runnable to remove the status and navigation bar after a delay
        hideHandler.removeCallbacks(showPart2Runnable)
        hideHandler.postDelayed(hidePart2Runnable, UI_ANIMATION_DELAY.toLong())
    }

    /**
     * Schedules a call to hide() in [delayMillis], canceling any
     * previously scheduled calls.
     */
    private fun delayedHide(delayMillis: Int) {
        hideHandler.removeCallbacks(hideRunnable)
        hideHandler.postDelayed(hideRunnable, delayMillis.toLong())
    }

    companion object {
        /**
         * Whether or not the system UI should be auto-hidden after
         * [AUTO_HIDE_DELAY_MILLIS] milliseconds.
         */
        private const val AUTO_HIDE = true

        /**
         * If [AUTO_HIDE] is set, the number of milliseconds to wait after
         * user interaction before hiding the system UI.
         */
        private const val AUTO_HIDE_DELAY_MILLIS = 3000

        /**
         * Some older devices needs a small delay between UI widget updates
         * and a change of the status and navigation bar.
         */
        private const val UI_ANIMATION_DELAY = 300
    }
}