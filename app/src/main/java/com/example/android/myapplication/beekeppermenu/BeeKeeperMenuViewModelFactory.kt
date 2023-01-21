package com.example.android.myapplication.beekeppermenu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BeeKeeperMenuViewModelFactory : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BeeKeeperMenuViewModel::class.java)){
            return BeeKeeperMenuViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}