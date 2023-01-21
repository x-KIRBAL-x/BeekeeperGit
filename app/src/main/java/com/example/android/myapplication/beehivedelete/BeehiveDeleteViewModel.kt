package com.example.android.myapplication.beehivedelete

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.myapplication.database.BeeDatabaseDao
import com.example.android.myapplication.database.Beehive
import kotlinx.coroutines.launch

class BeehiveDeleteViewModel(
    private val beehivekey: Long,
    private val beeGroupKey: Long,
    dataSource: BeeDatabaseDao): ViewModel() {

     val database = dataSource

    val _navigateToBeehiveDetailFragment = MutableLiveData<Boolean?>()
    val _navigateToBeehivesFragment = MutableLiveData<Boolean?>()

    val navigateToBeehiveDetailFragment: LiveData<Boolean?>
        get() = _navigateToBeehiveDetailFragment

    val navigateToBeehivesFragment: LiveData<Boolean?>
        get() = _navigateToBeehivesFragment

    fun doneNavigateToBeehiveDetailFragment(){
        _navigateToBeehiveDetailFragment.value = null
    }

    fun doneNavigateToBeehivesFragment(){
        _navigateToBeehivesFragment.value = null
    }

    suspend fun deleteBeehive(beehive: Beehive){
        database.deleteBeehiveWithId(beehive.beehiveId)
    }

    fun clickOnYesButton(){
        viewModelScope.launch {
            val current_Beehive = database.getBeeHive(beehivekey)
            deleteBeehive(current_Beehive)
            _navigateToBeehivesFragment.value = true
        }
    }

    fun clickOnNoButton(){
        _navigateToBeehiveDetailFragment.value = true
    }
}