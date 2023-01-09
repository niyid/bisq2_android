package com.bisq2.mobile.android.ui.home.properties

import com.bisq2.mobile.android.R

class MoneroSwaps: TradeAppsUIText {
    override fun headerText(): String {
        return "Monero Swaps"
    }

    override fun logo(): Int {
        return R.drawable.ic_protocol_monero2x
    }

    override fun description(): String {
        return "Trade Bitcoin and Monero using an atomic cross chain swap."
    }

    override fun marketsToBeUsedIn(): String {
        return "XMR/BTC"
    }

    override fun releasePeriod(): String {
        return "Q3/22"
    }

    override fun securityRating(): Int {
        return 3
    }

    override fun privacyRating(): Int {
        return 3
    }

    override fun convenienceRating(): Int {
        return 1
    }
}