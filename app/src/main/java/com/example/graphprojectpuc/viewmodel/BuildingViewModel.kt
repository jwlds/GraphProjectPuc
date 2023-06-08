package com.example.graphprojectpuc.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.graphprojectpuc.database.FirebaseHelper.Companion.getDatabase
import com.example.graphprojectpuc.model.Building

class BuildingViewModel : ViewModel() {

    private val _buildingList = MutableLiveData<List<Building>>()
    val buildingList: LiveData<List<Building>> = _buildingList

    init {
        val buildingRef = getDatabase().collection("buildings").orderBy("index")
        buildingRef.addSnapshotListener { querySnapshot, error ->
            if (error != null) {
                return@addSnapshotListener
            }

            if (querySnapshot != null) {
                val buildings = mutableListOf<Building>()
                for (document in querySnapshot.documents) {
                    val data = document.toObject(Building::class.java)
                    data?.let { buildings.add(it) }
                }
                _buildingList.value = buildings
            }
        }
    }
}