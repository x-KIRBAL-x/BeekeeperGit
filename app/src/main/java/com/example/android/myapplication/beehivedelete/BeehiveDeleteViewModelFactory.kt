package com.example.android.myapplication.beehivedelete

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.myapplication.database.BeeDatabaseDao

class BeehiveDeleteViewModelFactory(
    private val beehivekey: Long,
    private val beeGroupKey: Long,
    private val dataSource: BeeDatabaseDao): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BeehiveDeleteViewModel::class.java)){
            return BeehiveDeleteViewModel(beehivekey,beeGroupKey,dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}