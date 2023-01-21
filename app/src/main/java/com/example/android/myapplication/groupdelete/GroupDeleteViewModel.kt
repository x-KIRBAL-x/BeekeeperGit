package com.example.android.myapplication.groupdelete

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.myapplication.database.BeeDatabaseDao
import com.example.android.myapplication.database.BeeGroup
import kotlinx.coroutines.launch

class GroupDeleteViewModel(
    private val groupKey: Long,
    dataSource: BeeDatabaseDao): ViewModel() {

    val database = dataSource

    private val _navigateToBeehivesFragment = MutableLiveData<Boolean?>()
    private val _navigateToBeeGroupsFragment = MutableLiveData<Boolean?>()

    val  navigateToBeehivesFragment: LiveData<Boolean?>
        get() = _navigateToBeehivesFragment

    val navigateToBeeGroupsFragment: LiveData<Boolean?>
        get() = _navigateToBeeGroupsFragment

    fun clickOnNoButton(){
        _navigateToBeehivesFragment.value=true
    }

    fun doneNavigatingToBeehivesFragment(){
        _navigateToBeehivesFragment.value=null
    }

    fun doneNavigateToBeeGroupsFragment(){
        _navigateToBeeGroupsFragment.value = null
    }

    suspend fun deleteGroup(group: BeeGroup){
        database.deleteGroupWithId(group.groupId)
    }

    fun clickOnDeleteGroup(){
        viewModelScope.launch {
            val current_group = database.getgroup(groupKey)
            deleteGroup(current_group)
            _navigateToBeeGroupsFragment.value=true
        }
    }

}