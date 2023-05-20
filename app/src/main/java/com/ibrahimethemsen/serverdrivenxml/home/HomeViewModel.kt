package com.ibrahimethemsen.serverdrivenxml.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ibrahimethemsen.serverdrivenxml.model.ItemActivity
import com.ibrahimethemsen.serverdrivenxml.model.ViewTypeModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val remoteConfig: FirebaseRemoteConfig,
    private val gson: Gson
) : ViewModel() {
    private val _homeScreenUi = MutableLiveData<List<ViewTypeModel>>()
    val homeScreenUi : LiveData<List<ViewTypeModel>> = _homeScreenUi

    private val _activityList = MutableLiveData<List<ItemActivity>>()
    val activityList : LiveData<List<ItemActivity>> = _activityList

    init {
        getHomeUi()
        getActivityList()
    }
    private fun getActivityList(){
        viewModelScope.launch {
            remoteConfig.fetchToLiveData("product_offer",gson,_activityList)
        }
    }

    private fun getHomeUi(){
        viewModelScope.launch {
            remoteConfig.fetchToLiveData("home_screen",gson,_homeScreenUi)
        }
    }
}


inline fun <reified T> FirebaseRemoteConfig.fetchToLiveData(
    key: String,
    gson: Gson,
    liveData: MutableLiveData<T>
) {
    this.fetchAndActivate().addOnCompleteListener {
        if (it.isSuccessful) {
            val keyString = this.getString(key)
            val type = object : TypeToken<T>() {}.type
            val jsonModel = gson.fromJson<T>(keyString, type)
            liveData.postValue(jsonModel)
        }
    }
}