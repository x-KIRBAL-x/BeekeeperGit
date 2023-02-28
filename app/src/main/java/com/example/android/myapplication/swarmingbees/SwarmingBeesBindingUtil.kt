package com.example.android.myapplication.swarmingbees

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.android.myapplication.R
import com.example.android.myapplication.convertNumericQualityToString
import com.example.android.myapplication.database.Beehive

@BindingAdapter("SwarmingBeesName")
fun TextView.setBeeName(item: Beehive?){
    item?.let {
        text = "Beehive name: " +  item.beehiveName
    }
}

@BindingAdapter("SwarmingBeesPopulation")
fun TextView.setSwarmingBeesPopulation(item: Beehive?){
    item?.let {
        text = "Population: " + convertNumericQualityToString(item.BeehivePopulation,context.resources)
    }
}

@BindingAdapter("SwarmingBeesBroodframeNumber")
fun TextView.setBroodframeNumber(item: Beehive?){
    item?.let {
        text = "Broodframe number: " + item.broodFrameNumber.toString()
    }
}

@BindingAdapter("SwarmingBeesImg")
fun ImageView.setBeeImage(item: Beehive?){
    item?.let {
        setImageResource(R.drawable.hive)
    }
}