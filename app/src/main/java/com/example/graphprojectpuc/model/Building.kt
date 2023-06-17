package com.example.graphprojectpuc.model

// Classe que representa um prédio
data class Building(
    val index: Int? = null,
    val name: String? = null,
    val description: String? = null,
    val connectedBuildings: List<ConnectedBuilding>? = null
)


