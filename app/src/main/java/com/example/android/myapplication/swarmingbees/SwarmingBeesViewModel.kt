package com.example.android.myapplication.swarmingbees

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.myapplication.database.BeeDatabaseDao

class SwarmingBeesViewModel(
    private val groupKey: Long,
    dataSource: BeeDatabaseDao): ViewModel() {

    val database = dataSource

    val beehives = database.getSwarmingBees(groupKey)

    private val _navigateToBeeReviewFragment = MutableLiveData<Long>()
    private val _navigateToSwarmingBeesDescriptionFragment = MutableLiveData<Boolean?>()
    private val _navigateToManagementFragment = MutableLiveData<Boolean?>()

    val navigateToBeeReviewFragment
        get() = _navigateToBeeReviewFragment

    val navigateToSwarmingBeesDescriptionFragment: LiveData<Boolean?>
        get() = _navigateToSwarmingBeesDescriptionFragment

    val navigateToManagementFragment: LiveData<Boolean?>
        get() = _navigateToManagementFragment

    fun onClickBeehive(id: Long){
        _navigateToBeeReviewFragment.value=id
    }

    fun donenavigateToBeeReviewFragment(){
        _navigateToBeeReviewFragment.value=null
    }

    fun clickOnInfoButton(){
        _navigateToSwarmingBeesDescriptionFragment.value=true
    }

    fun doneNavigateToSwarmingBeesDescriptionFragment(){
        _navigateToSwarmingBeesDescriptionFragment.value=null
    }

    fun clickOnCloseButton(){
        _navigateToManagementFragment.value=true
    }
    fun doneNavigateToManagementFragment(){
        _navigateToManagementFragment.value=null
    }
}