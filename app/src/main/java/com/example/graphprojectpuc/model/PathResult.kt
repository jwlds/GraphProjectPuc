package com.example.graphprojectpuc.model

// Classe que representa um resultado do Dijkstra
data class PathResult(
    val path: List<Int>,
    val estimatedTime: Int
)