package com.example.android.myapplication.addnewgroup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.myapplication.database.BeeDatabaseDao
import com.example.android.myapplication.database.BeeGroup
import kotlinx.coroutines.launch

class AddNewGroupViewModel(dataSource: BeeDatabaseDao,
                           private val groupKey: Long) : ViewModel(){

    val database = dataSource

    private val group: LiveData<BeeGroup>

    fun getGroup() = group

    init {
          group = database.getGroupWithId(groupKey)
    }

    private val _clickDoneButton = MutableLiveData<Boolean?>()

    val clickDoneButton: LiveData<Boolean?>
        get() = _clickDoneButton

    fun doneNavigatingToGroupsFragment(){
        _clickDoneButton.value=null
    }

    fun clickDoneButton(){
        _clickDoneButton.value=true
    }

    fun setvalue(nev: String, hely: String){
        viewModelScope.launch {
            val newgroup = database.getgroup(groupKey)
            newgroup.groupNev = nev
            newgroup.groupHely = hely
            database.updateGroup(newgroup)
        }
    }
}