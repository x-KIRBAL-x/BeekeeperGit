package com.example.android.myapplication.beehivedetail

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.android.myapplication.database.Beehive
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("QueenBeeAge")
fun TextView.setQueenBeeAge(item: Beehive?) {
    item?.let {
        text ="QueenBee Age: " + ((SimpleDateFormat("yyyy").format(Date()).toString().toInt())-item.queenBeeYear).toString() + " years"
    }
}

@BindingAdapter("LastReview")
fun TextView.setLastReview(item: Beehive?){
    item?.let {
        text = "Last review: " + item.lastManagement
    }
}