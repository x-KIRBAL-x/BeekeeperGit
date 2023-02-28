package com.example.android.myapplication.swarmingbees

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.myapplication.database.BeeDatabaseDao

class SwarmingBeesViewModelFactory(
    private val groupKey: Long,
    private val dataSource: BeeDatabaseDao): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SwarmingBeesViewModel::class.java)){
            return SwarmingBeesViewModel(groupKey,dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}