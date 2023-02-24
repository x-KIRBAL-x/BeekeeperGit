package com.example.android.myapplication.addnewbeehive

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.myapplication.database.BeeDatabaseDao

class AddNewBeehiveViewModelFactory(
    private val beeGroupKey: Long,
    private val beehiveKey: Long,
    private val navi: Long,
    private val dataSource: BeeDatabaseDao): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddNewBeehiveViewModel::class.java)){
            return AddNewBeehiveViewModel(beeGroupKey,beehiveKey,navi,dataSource) as T
        }
        throw IllegalArgumentException("Unknown View Model")
    }
}