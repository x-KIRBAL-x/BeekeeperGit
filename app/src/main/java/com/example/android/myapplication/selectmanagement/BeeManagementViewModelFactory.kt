package com.example.android.myapplication.selectmanagement

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.myapplication.database.BeeDatabaseDao

class BeeManagementViewModelFactory(
    private val beeGroupKey: Long,
    private val dataSource: BeeDatabaseDao): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BeeManagementViewModel::class.java)) {
            return BeeManagementViewModel(beeGroupKey, dataSource) as T
        }
        throw  IllegalArgumentException("Unknown ViewModel class")
    }
}