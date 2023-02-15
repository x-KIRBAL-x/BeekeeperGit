package com.example.android.myapplication.broodframebalancing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.myapplication.database.BeeDatabase
import com.example.android.myapplication.database.BeeDatabaseDao

class BroodFrameBalancingViewModel(
    private val groupKey: Long,
    dataSource: BeeDatabaseDao): ViewModel() {

    val database = dataSource

    val beehives = database.getBadBroodFrameBees(groupKey)

    private val _navigateToBeeReviewFragment = MutableLiveData<Long>()
    private val _navigateToBroodFrameBalancingDescriptionFragment = MutableLiveData<Boolean?>()
    private val _navigateToBeeManagementFragment = MutableLiveData<Boolean?>()

    val navigateToBeeReviewFragment
        get() = _navigateToBeeReviewFragment

    val navigateToBroodFrameBalancingDescriptionFragment: LiveData<Boolean?>
        get() = _navigateToBroodFrameBalancingDescriptionFragment

    val navigateToBeeManagementFragment: LiveData<Boolean?>
        get() = _navigateToBeeManagementFragment

    fun onClickBeehive(id: Long){
        _navigateToBeeReviewFragment.value=id
    }

    fun donenavigateToBeeReviewFragment(){
        _navigateToBeeReviewFragment.value=null
    }

    fun clickOnInfoButton(){
        _navigateToBroodFrameBalancingDescriptionFragment.value=true
    }

    fun doneNavigateToBroodFrameBalancingDescriptionFragment(){
        _navigateToBroodFrameBalancingDescriptionFragment.value=null
    }

    fun clickOnCloseButton(){
        _navigateToBeeManagementFragment.value=true
    }

    fun doneNavigateToBeeManagementFragment(){
        _navigateToBeeManagementFragment.value=null
    }

}