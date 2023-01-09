package com.bisq2.mobile.android.ui.home.properties

class BsqSwaps :TradeAppsUIText {
    override fun headerText(): String {
        return "BSQ Swaps"
    }

    override fun logo(): Int {
        return 1
    }

    override fun description(): String {
        return "Trade Bitcoin and the BSQ token via atomic swaps, instantaneously and secure."
    }

    override fun marketsToBeUsedIn(): String {
        return "Any BTC market"
    }

    override fun releasePeriod(): String {
        return "Q3/22"
    }

    override fun securityRating(): Int {
        return 2
    }

    override fun privacyRating(): Int {
        return 2
    }

    override fun convenienceRating(): Int {
        return 2
    }
}