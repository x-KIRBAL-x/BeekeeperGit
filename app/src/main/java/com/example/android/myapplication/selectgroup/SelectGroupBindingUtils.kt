package com.example.android.myapplication.selectgroup

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.android.myapplication.R
import com.example.android.myapplication.database.BeeGroup

@BindingAdapter("SelectGroupImage")
fun ImageView.setGroupImage(item: BeeGroup?){
    item?.let {
        setImageResource(R.drawable.group)
    }
}

@BindingAdapter("SelectGroupName")
fun TextView.setGroupName(item: BeeGroup?){
    item?.let {
        text = item.groupNev
    }
}