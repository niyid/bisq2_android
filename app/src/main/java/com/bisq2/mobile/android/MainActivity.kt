package com.bisq2.mobile.android

import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bisq2.mobile.android.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import org.torproject.jni.TorService


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var tradeAppsMenuItem: MenuItem
    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var progressBar: ProgressBar
    private val logTag = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

//        binding.appBarMain.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
        drawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_bisq_easy, R.id.nav_bisq_multisig, R.id.nav_bsq_swaps,
                R.id.nav_chat, R.id.nav_dashboard, R.id.nav_events, R.id.nav_learn, R.id.nav_wallet,
                R.id.nav_settings, R.id.nav_liquid_swaps, R.id.nav_bisq_multisig, R.id.nav_xmr_swaps,
                R.id.nav_lightning_mpc, R.id.nav_bsq_swaps
            ), drawerLayout
        )
        navView.itemIconTintList = null
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navView.setNavigationItemSelectedListener(object : MenuItem.OnMenuItemClickListener,
            NavigationView.OnNavigationItemSelectedListener {
            override fun onMenuItemClick(item: MenuItem?): Boolean {
                Log.i("MainActivity", "onMenuItemClick-$item")
                invalidateOptionsMenu()
                return true
            }

            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                Log.i(logTag, "onNavigationItemSelected-$item")
                when(item.itemId) {
                    R.id.nav_bisq_easy -> {
                        Log.i(logTag, "onNavigationItemSelected-${item.itemId}")
                        try {
                            navController.navigate(R.id.transition_2_bisq_easy)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                    R.id.nav_dashboard -> {
                        Log.i(logTag, "onNavigationItemSelected-${item.itemId}")
                        try {
                            navController.navigate(R.id.transition_2_trading_home)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
                item.isChecked = true
                // Set action bar title
                title = item.title
                invalidateOptionsMenu()

                drawerLayout.closeDrawer(GravityCompat.START)
                return true
            }

        })

        val navMenu = navView.menu
        tradeAppsMenuItem = navMenu.getItem(1)

        val tradeAppsSubMenu = tradeAppsMenuItem.subMenu
        Log.i(logTag, "tradeAppsSubMenu: $tradeAppsSubMenu")

        progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun startButtonClick(view: View) {
        registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent) {
                val status: String? = intent.getStringExtra(TorService.EXTRA_STATUS)
                Toast.makeText(context, status, Toast.LENGTH_SHORT).show()
            }
        }, IntentFilter(TorService.ACTION_STATUS))
        bindService(Intent(this, TorService::class.java), object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder) {
                progressBar.visibility = View.VISIBLE
                //moved torService to a local variable, since we only need it once
                val torService: TorService = (service as TorService.LocalBinder).getService()
                while (torService.getTorControlConnection() == null) {
                    try {
                        Thread.sleep(500)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
                progressBar.visibility = View.GONE
                torService.dataDir
                val torrc = TorService.getTorrc(applicationContext)
                val torrcDef = TorService.getDefaultsTorrc(applicationContext)
                Log.i(logTag, "Tor torrc: $torrc")
                Log.i(logTag, "Tor defaults: $torrcDef")
                Log.i(logTag, "Tor data dir: ${torService.dataDir}")
                Log.i(logTag, "Tor files dir: ${torService.filesDir}")
                Log.i(logTag, "Tor cache dir: ${torService.cacheDir}")

                Toast.makeText(this@MainActivity, "Got Tor control connection", Toast.LENGTH_LONG)
                    .show()
            }

            override fun onServiceDisconnected(name: ComponentName?) {}
        }, BIND_AUTO_CREATE)
    }

    fun createOffer(view: View) {
        startActivity(Intent(this, TradeActivity::class.java))
    }
}