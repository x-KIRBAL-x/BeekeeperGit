package com.example.android.myapplication.selectgroup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.myapplication.database.BeeDatabaseDao

class SelectGroupViewModel(dataSource: BeeDatabaseDao): ViewModel() {

    val database = dataSource

    val groups = database.getAllGroups()

    private val _navigateToBeeMenuFragment = MutableLiveData<Boolean?>()
    private val _navigateToSelectManagementFragment = MutableLiveData<Long>()

    val navigateToBeeMenuFragment: LiveData<Boolean?>
        get() = _navigateToBeeMenuFragment

    val navigateToSelectManagementFragment
        get() = _navigateToSelectManagementFragment

    fun doneNavigatingMenuFragment(){
        _navigateToBeeMenuFragment.value = null
    }

    fun doneNavigatingToSelectManagementFragment(){
        _navigateToSelectManagementFragment.value=null
    }

    fun onBeeGroupsClicked(id: Long){
        _navigateToSelectManagementFragment.value=id
    }

    fun clickOnClose(){
        _navigateToBeeMenuFragment.value=true
    }
}