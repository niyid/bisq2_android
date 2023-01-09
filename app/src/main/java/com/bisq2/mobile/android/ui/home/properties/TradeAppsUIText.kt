package com.bisq2.mobile.android.ui.home.properties

interface TradeAppsUIText {
    fun headerText() : String
    fun logo() : Int
    fun description() : String
    fun marketsToBeUsedIn() : String
    fun releasePeriod() : String
    fun securityRating() : Int = 0
    fun privacyRating() : Int = 0
    fun convenienceRating() : Int = 0
}