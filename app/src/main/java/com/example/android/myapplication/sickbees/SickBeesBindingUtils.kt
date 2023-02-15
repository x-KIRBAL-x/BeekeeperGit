package com.example.android.myapplication.sickbees

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.android.myapplication.R
import com.example.android.myapplication.convertNumericSickToString
import com.example.android.myapplication.database.Beehive

@BindingAdapter("SickBeeName")
fun TextView.setBeeName(item: Beehive?){
    item?.let {
        text = "Beehive name: " +  item.beehiveName
    }
}

@BindingAdapter("BeeSick")
fun TextView.setBeeSick(item: Beehive?){
    item?.let {
        text = convertNumericSickToString(item.noszema + item.AscosphaeraApis, context.resources)
    }
}

@BindingAdapter("SickBeeImage")
fun ImageView.setBeeImage(item: Beehive?){
    item?.let {
        setImageResource(R.drawable.hive)
    }
}