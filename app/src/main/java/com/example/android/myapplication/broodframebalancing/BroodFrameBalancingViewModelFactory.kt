package com.example.android.myapplication.broodframebalancing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.myapplication.database.BeeDatabaseDao

class BroodFrameBalancingViewModelFactory(
    private val groupKey: Long,
    private val dataSource: BeeDatabaseDao): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BroodFrameBalancingViewModel::class.java)){
            return BroodFrameBalancingViewModel(groupKey,dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}