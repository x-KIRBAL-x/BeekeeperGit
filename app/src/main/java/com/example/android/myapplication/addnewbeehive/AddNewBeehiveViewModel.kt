package com.example.android.myapplication.addnewbeehive

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.myapplication.database.BeeDatabaseDao
import com.example.android.myapplication.database.Beehive
import kotlinx.coroutines.launch

class AddNewBeehiveViewModel(
    private val beeGroupKey: Long,
    private val beehiveKey: Long,
    private val navi: Long,
    dataSource: BeeDatabaseDao) : ViewModel() {


    val database = dataSource

    private val beehive: LiveData<Beehive>

    init {
        beehive = database.getBeehiveWithId(beehiveKey)
    }

    fun getBeehive() = beehive

    private val _clickDoneButton = MutableLiveData<Int?>()

    val clickDoneButton: LiveData<Int?>
        get() = _clickDoneButton

    fun clickDoneButton(){
        _clickDoneButton.value = navi.toInt()
    }

    fun donenavigating(){
        _clickDoneButton.value = null
    }

    fun setValue(name: String, queenyear: Int){
        viewModelScope.launch {
            val newBeeHive = database.getBeeHive(beehiveKey)
            val beegroup = database.getgroup(beeGroupKey)
            newBeeHive.beehiveName = name
            newBeeHive.queenBeeYear = queenyear
            newBeeHive.groupId = beeGroupKey
            newBeeHive.groupName = beegroup.groupNev
            database.updateHive(newBeeHive)
        }
    }

    fun updateBeehive(newName: String, queenyear: Int){
        viewModelScope.launch {
            val newBeehive = beehive.value ?: return@launch
            newBeehive.beehiveName = newName
            newBeehive.queenBeeYear = queenyear
            database.updateHive(newBeehive)
        }
    }
}