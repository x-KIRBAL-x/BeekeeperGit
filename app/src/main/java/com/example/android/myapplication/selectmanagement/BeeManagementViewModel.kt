package com.example.android.myapplication.selectmanagement

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.myapplication.database.BeeDatabaseDao

class BeeManagementViewModel(private val groupKey: Long,
                             dataSource: BeeDatabaseDao): ViewModel() {


    private val _navigateToBroodFrameBalancingFragment = MutableLiveData<Boolean?>()
    private val _navigateToQueenBeeManagementFragment = MutableLiveData<Boolean?>()
    private val _navigateToSelectGroupFragment = MutableLiveData<Boolean?>()

    val navigateToBroodFrameBalancingFragment: LiveData<Boolean?>
        get() = _navigateToBroodFrameBalancingFragment

    val navigateToQueenBeeManagementFragment: LiveData<Boolean?>
        get() = _navigateToQueenBeeManagementFragment

    val navigateToSelectGroupFragment: LiveData<Boolean?>
        get() = _navigateToSelectGroupFragment

    fun clickOnBroodFrameBalancingButton(){
        _navigateToBroodFrameBalancingFragment.value=true
    }

    fun donenavigateToBroodFrameBalancingFragment(){
        _navigateToBroodFrameBalancingFragment.value= null
    }

    fun clickOnQueenBeeManagementButton(){
        _navigateToQueenBeeManagementFragment.value=true
    }

    fun doneNavigatingToQueenBeeMAnagementFragment(){
        _navigateToQueenBeeManagementFragment.value=null
    }

    fun clickOnCloseButton(){
        _navigateToSelectGroupFragment.value=true
    }

    fun doneNavigateToSelectGroupFragment(){
        _navigateToSelectGroupFragment.value=null
    }

}
