package com.example.android.myapplication.queenbeemanagement

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.android.myapplication.R
import com.example.android.myapplication.convertNumericQualityToString
import com.example.android.myapplication.database.Beehive

@BindingAdapter("QueenBeehiveImage")
fun ImageView.setQueenBeeimage(item: Beehive?){
    item?.let {
        setImageResource(R.drawable.hive)
    }
}

@BindingAdapter("QueenBeeName")
fun TextView.setBeehiveName(item: Beehive?){
    item?.let {
        text = item.beehiveName
    }
}

@BindingAdapter("QueenBeeAgeManagement")
fun TextView.setQueenBeeAge(item: Beehive?){
    item?.let {
        text = item.queenBeeYear.toString()
    }
}

@BindingAdapter("QueenBeeConditionManagement")
fun TextView.setQuennBeeCondition(item: Beehive?){
    item?.let {
        text = convertNumericQualityToString(item.queenBeeState,context.resources)
    }
}