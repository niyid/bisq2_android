package com.bisq2.mobile.android.ui.home.properties

class LightningMpc : TradeAppsUIText {
    override fun headerText(): String {
        return "Lightning MPC"
    }

    override fun logo(): Int {
        return 1
    }

    override fun description(): String {
        return "3 party trade protocol over Lightning network using multi-party computation Cryptography"
    }

    override fun marketsToBeUsedIn(): String {
        return "Any BTC-LN market"
    }

    override fun releasePeriod(): String {
        return "Q4/22"
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