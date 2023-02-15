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

        private val _navigateToPreviousFragment = MutableLiveData<Int?>()
        private val _editBeequeenCondition = MutableLiveData<Boolean?>()
        private val _editBeehivePopulation = MutableLiveData<Boolean?>()
        private val _editBroodframeQuantity = MutableLiveData<Boolean?>()
        private val _editHoneyframeQuantity = MutableLiveData<Boolean?>()
        private val _navigateToNosemadescriptionFragment = MutableLiveData<Boolean?>()
        private val _navigateToAscosphaeraApisDescriptionFragment = MutableLiveData<Boolean?>()

    val navigateToPreviousFragment: LiveData<Int?>
        get() = _navigateToPreviousFragment

    val editBeequeenCondition: LiveData<Boolean?>
        get() = _editBeequeenCondition

    val editBeehivePopulation: LiveData<Boolean?>
        get() = _editBeehivePopulation

    val editBroodframeQuantity: LiveData<Boolean?>
        get() = _editBroodframeQuantity

    val editHoneyframeQuantity: LiveData<Boolean?>
        get() = _editHoneyframeQuantity

    val navigateToNosemadescriptionFragment: LiveData<Boolean?>
        get() = _navigateToNosemadescriptionFragment

    val navigateToAscosphaeraApisDescriptionFragment: LiveData<Boolean?>
        get() = _navigateToAscosphaeraApisDescriptionFragment


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

    fun doneReview(date: String, broodframenumber: Int, honeyframenumber: Int, nosema: Int, ascosphaeraApis: Int, queenbeeYear: Int){
        viewModelScope.launch {
            val newReview = beehive.value ?: return@launch
            newReview.lastManagement = date
            newReview.broodFrameNumber = broodframenumber
            newReview.honeyFrameNumber = honeyframenumber
            newReview.noszema = nosema
            newReview.AscosphaeraApis = ascosphaeraApis
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
        _navigateToPreviousFragment.value=navi.toInt()
    }
    fun doneNavigating(){
        _navigateToPreviousFragment.value=null
    }

    fun clickOnNosemaInfoButton(){
        _navigateToNosemadescriptionFragment.value=true
    }

    fun donenavigateToNosemadescriptionFragment(){
        _navigateToNosemadescriptionFragment.value=null
    }

    fun clickOnAscosphaeraApisInfoButton(){
        _navigateToAscosphaeraApisDescriptionFragment.value=true
    }

    fun doneNavigateToAscosphaeraApisDescriptionFragment(){
        _navigateToAscosphaeraApisDescriptionFragment.value=null
    }
}