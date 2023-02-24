package com.example.android.myapplication.beehivedetail

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.android.myapplication.R
import com.example.android.myapplication.database.Beehive
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("BeehiveName")
fun TextView.setBeehiveName(item: Beehive?){
    item?.let {
        text = "Beehive name: " + item.beehiveName
    }
}

@BindingAdapter("QueenBeeAge")
fun TextView.setQueenBeeAge(item: Beehive?) {
    item?.let {
        text ="QueenBee Age: " + ((SimpleDateFormat("yyyy").format(Date()).toString().toInt())-item.queenBeeYear).toString() + " years"
    }
}

@BindingAdapter("LastReview")
fun TextView.setLastReview(item: Beehive?){
    item?.let {
        text = "Last review: \n" + item.lastManagement
    }
}

@BindingAdapter("QueenBeeColor")
fun ImageView.setColorImage(item: Beehive?){
    item?.let {
        when (item.queenBeeYear.mod(5)){
            0 -> setImageResource(R.drawable.blue)
            1 -> setImageResource(R.drawable.white)
            2 -> setImageResource(R.drawable.yellow)
            3 -> setImageResource(R.drawable.red)
            else -> {setImageResource(R.drawable.green)}
        }
    }
}