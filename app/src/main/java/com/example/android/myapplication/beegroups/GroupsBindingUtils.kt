package com.example.android.myapplication.beegroups

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.android.myapplication.R
import com.example.android.myapplication.database.BeeGroup

@BindingAdapter("GroupImage")
fun ImageView.setGroupImg(item: BeeGroup?){
    item?.let {
        setImageResource(R.drawable.group)
    }
}

@BindingAdapter("GroupName")
fun TextView.setGroupName(item: BeeGroup?){
    item?.let {
        text = item.groupNev
    }
}