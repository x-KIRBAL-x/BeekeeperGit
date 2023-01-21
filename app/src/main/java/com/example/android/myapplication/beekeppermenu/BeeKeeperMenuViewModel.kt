package com.example.android.myapplication.beekeppermenu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job

class BeeKeeperMenuViewModel : ViewModel() {

    private val _clickreview = MutableLiveData<Boolean?>()

    private val _navigateToBeeGroupsFragment = MutableLiveData<Boolean?>()
    private val _navigateToSelectGroupFragment = MutableLiveData<Boolean?>()

    val navigateToBeeGroupsFragment: LiveData<Boolean?>
        get() = _navigateToBeeGroupsFragment

    val navigateToSelectGroupFragment: LiveData<Boolean?>
        get() = _navigateToSelectGroupFragment

    fun clickmanagementbutton(){
        _navigateToSelectGroupFragment.value=true
    }

    fun clickmanagementbuttondone(){
       _navigateToSelectGroupFragment.value=null
    }

    fun doneNavigating(){
        _navigateToBeeGroupsFragment.value = null
    }

    fun click(){
        _navigateToBeeGroupsFragment.value=true
    }

}