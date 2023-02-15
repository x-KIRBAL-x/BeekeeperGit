package com.example.android.myapplication.beestatistics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.myapplication.database.BeeDatabaseDao

class BeeStatisticsViewModelFactory(
    private val groupKey: Long,
    private val dataSource: BeeDatabaseDao): ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(BeeStatisticsViewModel::class.java)){
            return BeeStatisticsViewModel(groupKey,dataSource) as T
    }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}