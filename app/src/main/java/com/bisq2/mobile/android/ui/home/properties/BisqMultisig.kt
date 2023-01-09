package com.bisq2.mobile.android.ui.home.properties

import com.bisq2.mobile.android.R

class BisqMultisig : TradeAppsUIText {
    override fun headerText(): String {
        return "Bisq Multisig"
    }

    override fun logo(): Int {
        return R.drawable.ic_protocol_bisq2x
    }

    override fun description(): String {
        return "The new Bisq Multisig protocol based on a single transaction and Taproot."
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