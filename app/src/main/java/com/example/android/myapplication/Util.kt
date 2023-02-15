package com.example.android.myapplication

import android.content.res.Resources

fun convertNumericQualityToString(quality: Int, resources: Resources): String{
    var qualityString = ""
    when (quality){
        0 -> qualityString = resources.getString(R.string.zero)
        1 -> qualityString = resources.getString(R.string.one)
        2 -> qualityString = resources.getString(R.string.two)
        3 -> qualityString = resources.getString(R.string.three)
        4 -> qualityString = resources.getString(R.string.four)
        5 -> qualityString = resources.getString(R.string.five)
    }
    return qualityString
}

fun convertNumericQuantityToString(quantity: Int, resources: Resources): String{
    var quantityString = ""
    when (quantity){
        1 -> quantityString = resources.getString(R.string.one_quantity)
        2 -> quantityString = resources.getString(R.string.two_quantity)
        3 -> quantityString = resources.getString(R.string.three_quantity)
    }
    return quantityString
}

fun convertNumericSickToString(diseases: Int, resources: Resources): String{
    var diseasesString = ""
    when (diseases){
        1 -> diseasesString = resources.getString(R.string.nosema)
        10 -> diseasesString = resources.getString(R.string.ascosphaera_apis)
        11-> diseasesString = resources.getString(R.string.ascosphaera_apis_and_nosema)
    }
    return diseasesString
}