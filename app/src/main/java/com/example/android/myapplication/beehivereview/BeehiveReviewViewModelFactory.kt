package com.example.android.myapplication.beehivereview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.myapplication.database.BeeDatabaseDao

class BeehiveReviewViewModelFactory(
    private val beehiveKey: Long,
    private val beegroupKey: Long,
    private val navi: Long,
    private val dataSource: BeeDatabaseDao): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BeehiveReviewViewModel::class.java)){
            return BeehiveReviewViewModel(beehiveKey,dataSource,beegroupKey,navi) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}