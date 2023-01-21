package com.example.android.myapplication.beehivedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.myapplication.database.BeeDatabaseDao

class BeehiveDetailViewModelFactory(
    private val beehiveKey: Long,
    private val beeGroupKey: Long,
    private val dataSource: BeeDatabaseDao): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(BeehiveDetailViewModel::class.java)){
            return BeehiveDetailViewModel(beehiveKey,beeGroupKey,dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}