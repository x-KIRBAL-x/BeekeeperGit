package com.example.android.myapplication.selectmanagement

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.myapplication.database.BeeDatabaseDao

class BeeManagementViewModel(private val groupKey: Long,
                             dataSource: BeeDatabaseDao): ViewModel() {

    private val _navigateToQueenBeeManagementFragment = MutableLiveData<Boolean?>()
    private val _navigateToBroodFrameBalancingFragment = MutableLiveData<Boolean?>()
    private val _navigateToHoneyFrameBalancingFragment = MutableLiveData<Boolean?>()
    private val _navigateToSickBeesFragment = MutableLiveData<Boolean?>()
    private val _navigateToBeeStatisticsFragment = MutableLiveData<Boolean?>()
    private val _navigateToSelectGroupFragment = MutableLiveData<Boolean?>()


    val navigateToQueenBeeManagementFragment: LiveData<Boolean?>
        get() = _navigateToQueenBeeManagementFragment

    val navigateToBroodFrameBalancingFragment: LiveData<Boolean?>
        get() = _navigateToBroodFrameBalancingFragment

    val navigateToHoneyFrameBalancingFragment: LiveData<Boolean?>
        get() = _navigateToHoneyFrameBalancingFragment

    val navigateToSickBeesFragment: LiveData<Boolean?>
        get() = _navigateToSickBeesFragment

    val navigateToBeeStatisticsFragment: LiveData<Boolean?>
        get() = _navigateToBeeStatisticsFragment

    val navigateToSelectGroupFragment: LiveData<Boolean?>
        get() = _navigateToSelectGroupFragment

    fun clickOnQueenBeeManagementButton(){
        _navigateToQueenBeeManagementFragment.value=true
    }

    fun doneNavigatingToQueenBeeMAnagementFragment(){
        _navigateToQueenBeeManagementFragment.value=null
    }

    fun clickOnBroodFrameBalancingButton(){
        _navigateToBroodFrameBalancingFragment.value=true
    }

    fun donenavigateToBroodFrameBalancingFragment(){
        _navigateToBroodFrameBalancingFragment.value=null
    }

    fun clickOnHoneyFrameBalancingButton(){
        _navigateToHoneyFrameBalancingFragment.value=true
    }

    fun doneNavigatingToHoneyFrameBalancingFragment(){
        _navigateToHoneyFrameBalancingFragment.value=null
    }

    fun clickOnStatisticsButton(){
        _navigateToBeeStatisticsFragment.value=true
    }

    fun doneNavigatingToBeeStatisticsFragment(){
        _navigateToBeeStatisticsFragment.value=null
    }

    fun clickOnSickBeesButton(){
        _navigateToSickBeesFragment.value=true
    }

    fun doneNavigatingToSickBeesFragment(){
        _navigateToSickBeesFragment.value=null
    }

    fun clickOnCloseButton(){
        _navigateToSelectGroupFragment.value=true
    }

    fun doneNavigateToSelectGroupFragment(){
        _navigateToSelectGroupFragment.value=null
    }

}
