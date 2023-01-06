package com.bisq2.mobile.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.bisq2.mobile.android.databinding.ActivityMainBinding
import com.bisq2.mobile.android.databinding.ActivityTradeBinding
import com.google.android.material.tabs.TabLayoutMediator


class TradeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTradeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTradeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        fragmentAdapter.addFragment(FragmentTradeBtc.newInstance("", ""),"Trade")
        fragmentAdapter.addFragment(FragmentSelectMarket.newInstance(10),"Select Market")
        fragmentAdapter.addFragment(FragmentTradeAmount.newInstance("", ""),"Trade Amount")
        fragmentAdapter.addFragment(FragmentPaymentMethod.newInstance("", ""),"Payment Method")
        fragmentAdapter.addFragment(FragmentPostOffer.newInstance("", ""),"Post Offer")
        binding.pager.apply {
            adapter = fragmentAdapter
        }
        TabLayoutMediator(binding.tabLayout, binding.pager, true) { tab, position ->
            tab.text = fragmentAdapter.getPageTitle(position)
        }.attach()

    }
}