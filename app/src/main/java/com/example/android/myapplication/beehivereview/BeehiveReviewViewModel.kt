package com.example.android.myapplication.beehivereview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.myapplication.database.BeeDatabaseDao
import com.example.android.myapplication.database.Beehive
import kotlinx.coroutines.launch

class BeehiveReviewViewModel(
    private val beehiveKey: Long,
    dataSource: BeeDatabaseDao,
    private val beegroupKey: Long,
    private val navi: Long): ViewModel() {

        val database = dataSource

        private val beehive: LiveData<Beehive>

        init {
            beehive = database.getBeehiveWithId(beehiveKey)
        }

        fun getBeehive() = beehive

        private val _navigateToDetailFragment = MutableLiveData<Int?>()
        private val _editBeequeenCondition = MutableLiveData<Boolean?>()
        private val _editBeehivePopulation = MutableLiveData<Boolean?>()
        private val _editBroodframeQuantity = MutableLiveData<Boolean?>()
        private val _editHoneyframeQuantity = MutableLiveData<Boolean?>()

    val navigateToDetailFragment: LiveData<Int?>
        get() = _navigateToDetailFragment

    val editBeequeenCondition: LiveData<Boolean?>
        get() = _editBeequeenCondition

    val editBeehivePopulation: LiveData<Boolean?>
        get() = _editBeehivePopulation

    val editBroodframeQuantity: LiveData<Boolean?>
        get() = _editBroodframeQuantity

    val editHoneyframeQuantity: LiveData<Boolean?>
        get() = _editHoneyframeQuantity

    fun setBeequeenCondition(quality: Int){
        viewModelScope.launch {
            val newQuality = beehive.value ?: return@launch
            newQuality.queenBeeState = quality
            database.updateHive(newQuality)
        }
    }

    fun setBeehivePopulation(quality: Int){
        viewModelScope.launch {
            val newQuality = beehive.value ?: return@launch
            newQuality.BeehivePopulation = quality
            database.updateHive(newQuality)
        }
    }

    fun setBroodframeQuantity(quantity: Int){
        viewModelScope.launch {
            val newQuantity = beehive.value ?: return@launch
            newQuantity.broodFrame = quantity
            database.updateHive(newQuantity)
        }
    }

    fun setHoneyframeQuantity(quality: Int){
        viewModelScope.launch {
            val newQuantity = beehive.value ?: return@launch
            newQuantity.honeyFrame = quality
            database.updateHive(newQuantity)
        }
    }

    fun doneReview(date: String, broodframenumber: Int, honeyframenumber: Int,nosema: Int,meszes: Int,queenbeeYear: Int){
        viewModelScope.launch {
            val newReview = beehive.value ?: return@launch
            newReview.lastManagement = date
            newReview.broodFrameNumber = broodframenumber
            newReview.honeyFrameNumber = honeyframenumber
            newReview.noszema = nosema
            newReview.meszesedes = meszes
            newReview.queenBeeYear = queenbeeYear
            database.updateHive(newReview)
        }
    }

    fun clickOnEditBeequeenCoditionButton(){
        _editBeequeenCondition.value=true
    }

    fun doneEditBeequennCondition(){
        _editBeequeenCondition.value=null
    }

    fun clickOnEditBeehivePopulation(){
        _editBeehivePopulation.value=true
    }

    fun doneEditBeehivePopulation(){
        _editBeehivePopulation.value=null
    }

    fun clickOnEditBroodframeQuantityButton(){
        _editBroodframeQuantity.value=true
    }

    fun  doneEditBroodframeQuantity(){
        _editBroodframeQuantity.value=null
    }

    fun clickOnEditHoneyFrameQuantityButton(){
        _editHoneyframeQuantity.value=true
    }

    fun doneEditHoneyframeQuantity(){
        _editHoneyframeQuantity.value=null
    }

    fun clickOnDoneButton(){
        _navigateToDetailFragment.value=navi.toInt()
    }
    fun doneNavigating(){
        _navigateToDetailFragment.value=null
    }
}