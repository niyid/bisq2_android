package com.bisq2.mobile.android.ui.home.properties

import com.bisq2.mobile.android.R

class LiquidSwaps: TradeAppsUIText {
    override fun headerText(): String {
        return "Liquid Swaps"
    }

    override fun logo(): Int {
        return R.drawable.ic_protocol_liquid2x
    }

    override fun description(): String {
        return "Trade any Liquid based assets like USDT and BTC-L with an atomic swap on the liquid network."
    }

    override fun marketsToBeUsedIn(): String {
        return "Liquid Assets"
    }

    override fun releasePeriod(): String {
        return "Q2/22"
    }

    override fun securityRating(): Int {
        return 3
    }

    override fun privacyRating(): Int {
        return 3
    }

    override fun convenienceRating(): Int {
        return 2
    }
}