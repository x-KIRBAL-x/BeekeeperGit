package com.example.android.myapplication.beehives

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.android.myapplication.R
import com.example.android.myapplication.convertNumericQualityToString
import com.example.android.myapplication.database.BeeGroup
import com.example.android.myapplication.database.Beehive

@BindingAdapter("BeehiveImage")
fun ImageView.SetBeehiveImg(item: Beehive?){
    item?.let {
        setImageResource(R.drawable.hive)
    }
}

@BindingAdapter("BeehiveName")
fun TextView.setBeehiveName(item: Beehive?){
    item?.let {
        text = "Beehive Name: " + item.beehiveName
    }
}

@BindingAdapter("BeehiveNameListitem")
fun TextView.setBeehiveNameListItem(item: Beehive?){
    item?.let {
        text = item.beehiveName
    }
}

@BindingAdapter("BeeGroupName")
fun TextView.setBeeGroupName(item: BeeGroup?){
    item?.let {
        text = "Group name:     " + item.groupNev
    }
}

@BindingAdapter("BeeGroupLocation")
fun TextView.setBeeGroupLocation(item: BeeGroup?){
    item?.let {
        text = "Group location: " + item.groupHely
    }
}

