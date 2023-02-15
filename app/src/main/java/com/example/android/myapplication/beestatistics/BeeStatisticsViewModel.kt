package com.example.android.myapplication.beestatistics

import androidx.lifecycle.ViewModel
import com.example.android.myapplication.database.BeeDatabaseDao
import java.text.SimpleDateFormat
import java.util.*

class BeeStatisticsViewModel(
    private val groupKey: Long,
    dataSource: BeeDatabaseDao): ViewModel() {


    val database = dataSource

    fun getCountBadPop(index: Int): Int{
        val count: Int? = database.getAllBadPopulationBee(groupKey, index)
        return if(count==null){
            0
        } else{
            count
        }
    }
    fun getQueenBeeYear(index: Int): Int{
        val count: Int? = database.getCountQueenBeeAge(groupKey,
            SimpleDateFormat("yyyy").format(Date()).toString().toInt()-index)
        return if(count==null){
            0
        } else{
            count
        }
    }

    fun getAllBadQueenbee(): Int{
        val count: Int? = database.getAllBadQueenBee(groupKey, SimpleDateFormat("yyyy").format(Date()).toString().toInt()-2)
        return if(count==null){
            0
        } else{
            count
        }
    }

    fun getAllQueenbee(): Int{
        val count: Int? = database.getAllQueenbee(groupKey)
        return if(count==null){
            0
        } else{
            count
        }
    }
}