package com.example.android.myapplication.addnewgroup

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.myapplication.database.BeeDatabaseDao

class AddNewGroupViewModelFactory(
    private val dataSource: BeeDatabaseDao,
    private val groupKey: Long,
    private val navi: Long) : ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddNewGroupViewModel::class.java)){
            return AddNewGroupViewModel(dataSource, groupKey, navi) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}