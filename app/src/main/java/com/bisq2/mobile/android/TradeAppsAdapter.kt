package com.bisq2.mobile.android

import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentActivity
import com.bisq2.mobile.android.ui.home.properties.*

class TradeAppsAdapter(private val context: FragmentActivity, private val uiTexts: Array<TradeAppsUIText>)
    : ArrayAdapter<TradeAppsUIText>(context, R.layout.trade_btc_list, uiTexts) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.trade_btc_list, null, true)

        val titleText = rowView.findViewById(R.id.title) as TextView
        val marketToBeSoldAt = rowView.findViewById(R.id.marketToBeSoldAt) as TextView
        val releasePeriod = rowView.findViewById(R.id.releasePeriod) as TextView
        val convenienceRating = rowView.findViewById(R.id.convenience_rating) as RatingBar
        val securityRating = rowView.findViewById(R.id.security_rating) as RatingBar
        val privacyRating = rowView.findViewById(R.id.privacy_rating) as RatingBar
        val imageView = rowView.findViewById(R.id.icon) as ImageView
        val subtitleText = rowView.findViewById(R.id.description) as TextView
        val selectTradeApp = rowView.findViewById(R.id.select_app) as Button


        titleText.text = uiTexts[position].headerText()
        marketToBeSoldAt.text = uiTexts[position].marketsToBeUsedIn()
        releasePeriod.text = uiTexts[position].releasePeriod()
        convenienceRating.rating = uiTexts[position].convenienceRating().toFloat()
        securityRating.rating = uiTexts[position].securityRating().toFloat()
        privacyRating.rating = uiTexts[position].privacyRating().toFloat()
        imageView.setImageResource(uiTexts[position].logo())
        subtitleText.text = uiTexts[position].description()

        selectTradeApp.setOnClickListener {
            val itemAtPos = this.getItem(position)
            Toast.makeText(this.context, "Click on item at $itemAtPos its item id $position", Toast.LENGTH_LONG).show()
        }
        return rowView
    }
}