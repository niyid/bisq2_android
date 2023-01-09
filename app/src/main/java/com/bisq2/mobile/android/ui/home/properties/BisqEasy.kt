package com.bisq2.mobile.android.ui.home.properties

import com.bisq2.mobile.android.R

class BisqEasy : TradeAppsUIText {
    override fun headerText(): String {
        return "Bisq Easy"
    }

    override fun logo(): Int {
        return R.drawable.ic_protocol_satoshi_square2x
    }

    override fun description(): String {
        return "Easy to use chat based trade protocol. Security is based on seller's reputation."
    }

    override fun marketsToBeUsedIn(): String {
        return "Any"
    }

    override fun releasePeriod(): String {
        return ""
    }

    override fun securityRating(): Int {
        return 1
    }

    override fun privacyRating(): Int {
        return 2
    }

    override fun convenienceRating(): Int {
        return 3
    }
}