package com.example.android.myapplication.honeyframebalancing

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.android.myapplication.R
import com.example.android.myapplication.convertNumericQuantityToString
import com.example.android.myapplication.database.Beehive

@BindingAdapter("HoneyFrameBalanceName")
fun TextView.setBeeName(item: Beehive?){
    item?.let {
        text = "Beehive name: " +  item.beehiveName
    }
}

@BindingAdapter("BeehiveHoneyFrameNumber")
fun TextView.setBroodFrameNumber(item: Beehive?){
    item?.let {
        text = "Honeyframe quantity: " + convertNumericQuantityToString(item.honeyFrame,context.resources)
    }
}

@BindingAdapter("HoneyFrameBalanceImage")
fun ImageView.setBeeImage(item: Beehive?){
    item?.let {
        setImageResource(R.drawable.hive)
    }
}