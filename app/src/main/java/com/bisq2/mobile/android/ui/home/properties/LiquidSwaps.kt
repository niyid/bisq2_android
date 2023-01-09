package com.bisq2.mobile.android.ui.home.properties

class LiquidSwaps: TradeAppsUIText {
    override fun headerText(): String {
        return "Liquid Swaps"
    }

    override fun logo(): Int {
        return 1
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