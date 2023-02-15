package com.example.android.myapplication.queenbeemanagement

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.myapplication.database.BeeDatabaseDao
import java.text.SimpleDateFormat
import java.util.*

class QueenBeeManagementViewModel(
    private val groupKey: Long,
     dataSource: BeeDatabaseDao): ViewModel() {

    private val _navigateToReviewFragment = MutableLiveData<Long>()
    private val _navigateToQuennbeeManagementDescriptionFragment = MutableLiveData<Boolean?>()
    private val _navigateToManagementFragment = MutableLiveData<Boolean?>()

    val database = dataSource

    val beehives = database.getBadQueenBees(
        groupKey,
        SimpleDateFormat("yyyy").format(Date()).toString().toLong() - 2)

    val navigateToReviewFragment
        get() = _navigateToReviewFragment

    val navigateToQuennbeeManagementDescriptionFragment: LiveData<Boolean?>
        get() =_navigateToQuennbeeManagementDescriptionFragment

    val navigateToManagementFragment: LiveData<Boolean?>
        get() = _navigateToManagementFragment


    fun doneNavigateToReviewFragment(){
        _navigateToReviewFragment.value=null
    }

    fun onClickBeehive(id: Long){
        _navigateToReviewFragment.value=id
    }

    fun clickOnInfoButton(){
        _navigateToQuennbeeManagementDescriptionFragment.value=true
    }

    fun donenavigateTonavigateToQuennbeeManagementDescriptionFragment(){
        _navigateToQuennbeeManagementDescriptionFragment.value=null
    }

    fun clickOnCloseButton(){
        _navigateToManagementFragment.value=true
    }
    fun doneNavigateToManagementFragment(){
        _navigateToManagementFragment.value=null
    }
}
