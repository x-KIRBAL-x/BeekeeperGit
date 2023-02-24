package com.example.android.myapplication.addnewgroup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.myapplication.database.BeeDatabaseDao
import com.example.android.myapplication.database.BeeGroup
import kotlinx.coroutines.launch

class AddNewGroupViewModel(dataSource: BeeDatabaseDao,
                           private val groupKey: Long,
                           private val navi: Long) : ViewModel(){

    val database = dataSource

    private val group: LiveData<BeeGroup>

    fun getGroup() = group

    init {
          group = database.getGroupWithId(groupKey)
    }

    private val _clickDoneButton = MutableLiveData<Int?>()

    val clickDoneButton: LiveData<Int?>
        get() = _clickDoneButton

    fun doneNavigatingToGroupsFragment(){
        _clickDoneButton.value=null
    }

    fun clickDoneButton(){
        _clickDoneButton.value=navi.toInt()
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