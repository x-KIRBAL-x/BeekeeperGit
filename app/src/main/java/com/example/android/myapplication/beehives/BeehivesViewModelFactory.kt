package com.example.android.myapplication.beehives

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.myapplication.database.BeeDatabaseDao

class BeehivesViewModelFactory(
    private val beeGroupKey: Long,
    private  val dataSource: BeeDatabaseDao): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(BeehivesViewModel::class.java)) {
            return BeehivesViewModel(beeGroupKey, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}