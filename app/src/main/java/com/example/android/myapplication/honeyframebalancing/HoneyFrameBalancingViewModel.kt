package com.example.android.myapplication.honeyframebalancing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.myapplication.database.BeeDatabaseDao

class HoneyFrameBalancingViewModel(
    private val groupKey: Long,
    dataSource: BeeDatabaseDao): ViewModel(){

    val database = dataSource

    val beehives = database.getBadHoneyFrameBees(groupKey)

    private val _navigateToBeeReviewFragment = MutableLiveData<Long>()
    private val _navigateToHoneyFrameBalancingDescriptionFragment = MutableLiveData<Boolean?>()
    private val _navigateToBeeManagementFragment = MutableLiveData<Boolean?>()

    val navigateToBeeReviewFragment
        get() = _navigateToBeeReviewFragment

    val navigateToHoneyFrameBalancingDescriptionFragment: LiveData<Boolean?>
        get() = _navigateToHoneyFrameBalancingDescriptionFragment

    val navigateToBeeManagementFragment: LiveData<Boolean?>
        get() = _navigateToBeeManagementFragment

    fun onClickBeehive(id: Long){
        _navigateToBeeReviewFragment.value=id
    }

    fun donenavigateToBeeReviewFragment(){
        _navigateToBeeReviewFragment.value=null
    }

    fun clickOnInfoButton(){
        _navigateToHoneyFrameBalancingDescriptionFragment.value=true
    }

    fun doneNavigateToHoneyFrameBalancingDescriptionFragment(){
        _navigateToHoneyFrameBalancingDescriptionFragment.value=null
    }

    fun clickOnCloseButton(){
        _navigateToBeeManagementFragment.value=true
    }

    fun doneNavigateToBeeManagementFragment(){
        _navigateToBeeManagementFragment.value=null
    }
}