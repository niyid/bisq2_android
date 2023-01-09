package com.bisq2.mobile.android.ui.home.properties

class BisqEasy : TradeAppsUIText {
    override fun headerText(): String {
        return "Bisq Easy"
    }

    override fun logo(): Int {
        return 1
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