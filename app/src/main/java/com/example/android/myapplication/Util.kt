package com.example.android.myapplication

import android.content.res.Resources

fun convertNumericQualityToString(quality: Int, resources: Resources): String{
    var qualityString = resources.getString(R.string.three)
    when (quality){
        1 -> qualityString = resources.getString(R.string.one)
        2 -> qualityString = resources.getString(R.string.two)
        3 -> qualityString = resources.getString(R.string.three)
        4 -> qualityString = resources.getString(R.string.four)
        5 -> qualityString = resources.getString(R.string.five)
    }
    return qualityString
}

fun convertNumericQuantityToString(quantity: Int, resources: Resources): String{
    var quantityString = resources.getString(R.string.two_quantity)
    when (quantity){
        1 -> quantityString = resources.getString(R.string.one_quantity)
        2 -> quantityString = resources.getString(R.string.two_quantity)
        3 -> quantityString = resources.getString(R.string.three_quantity)
    }
    return quantityString
}