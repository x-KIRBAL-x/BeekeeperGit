package com.example.android.myapplication.beehivedetail

import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.myapplication.database.BeeDatabaseDao
import com.example.android.myapplication.database.Beehive
import kotlinx.coroutines.launch

class BeehiveDetailViewModel(
        private val beehiveKey: Long,
        private val beeGroupKey: Long,
        dataSource: BeeDatabaseDao): ViewModel() {

        val database = dataSource

        private val beehive: LiveData<Beehive>

        fun getBeehive() = beehive

        init {
            beehive=database.getBeehiveWithId(beehiveKey)
        }

        private val _navigateToBeehivesFragment = MutableLiveData<Boolean?>()
        private val _navgateToBeehiveDeleteFragment = MutableLiveData<Boolean?>()
        private val _navigateToBeehiveReviewFragment = MutableLiveData<Boolean?>()


        val navigateToBeehivesFragment: LiveData<Boolean?>
                get() = _navigateToBeehivesFragment
        val navgateToBeehiveDeleteFragment: LiveData<Boolean?>
                get() = _navgateToBeehiveDeleteFragment
        val navigateToBeehiveReviewFragment: LiveData<Boolean?>
                get() = _navigateToBeehiveReviewFragment

        fun doneNavigateToBeehivesFragment(){
                _navigateToBeehivesFragment.value= null
        }

        fun clickOnCloseButton(){
                _navigateToBeehivesFragment.value=true
        }

        fun doneNavgateToBeehiveDeleteFragment(){
                _navgateToBeehiveDeleteFragment.value = null
        }

        fun clickOnDeleteButton(){
                _navgateToBeehiveDeleteFragment.value = true
        }

        fun doneNavigateToBeehiveReviewFragment(){
                _navigateToBeehiveReviewFragment.value = null
        }

        fun clickOnReviewButton(){
                _navigateToBeehiveReviewFragment.value = true
        }

}