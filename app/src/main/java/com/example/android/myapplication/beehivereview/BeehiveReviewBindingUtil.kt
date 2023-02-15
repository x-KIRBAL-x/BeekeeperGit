package com.example.android.myapplication.beehivereview

import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.android.myapplication.convertNumericQualityToString
import com.example.android.myapplication.convertNumericQuantityToString
import com.example.android.myapplication.database.Beehive

@BindingAdapter("QueenbeeYearEdit")
fun EditText.setQueenbeeYear(item: Beehive?){
    item?.let {
        hint = item.queenBeeYear.toString()
    }
}


@BindingAdapter("QueenbeeCondition")
fun TextView.setQueenbeeCondition(item: Beehive?){
    item?.let {
        text = "Queenbee condition: " + convertNumericQualityToString(item.queenBeeState,context.resources)
    }
}

@BindingAdapter("BeehivePopulation")
fun TextView.setBeehivePopulation(item: Beehive?){
    item?.let {
        text = "Beehive Population: " + convertNumericQualityToString(item.BeehivePopulation,context.resources)
    }
}

@BindingAdapter("BroodframeQuantity")
fun TextView.setBroodframeQuantity(item: Beehive?){
    item?.let {
        text = "Broodframe quantity: " + convertNumericQuantityToString(item.broodFrame,context.resources)
    }
}

@BindingAdapter("HoneyframeQuantity")
fun TextView.setHoneyframeQuantity(item: Beehive?){
    item?.let {
        text = "Honeyframe quantity: " + convertNumericQuantityToString(item.honeyFrame,context.resources)
    }
}

@BindingAdapter("BroodframeNumber")
fun TextView.setBroodframeNumber(item: Beehive?){
    item?.let {
        if(item.broodFrameNumber != -1){
            hint = item.broodFrameNumber.toString()
        }
        else{
            hint = "Edit"
        }
    }
}

@BindingAdapter("HoneyframeNumber")
fun TextView.setHoneyframeNumber(item: Beehive?){
    item?.let {
        if(item.honeyFrameNumber != -1){
            hint = item.honeyFrameNumber.toString()
        }
        else{
            hint = "Edit"
        }
    }
}

@BindingAdapter("Nosema")
fun Switch.setNosemaValue(item: Beehive?){
    item?.let {
        isChecked = item.noszema==1
    }
}

@BindingAdapter("AscosphaeraApis")
fun Switch.setMeszesValue(item: Beehive?){
    item?.let {
        isChecked = item.AscosphaeraApis==10
    }
}