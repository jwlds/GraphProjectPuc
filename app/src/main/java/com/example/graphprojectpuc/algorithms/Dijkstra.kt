package com.example.graphprojectpuc.algorithms

import com.example.graphprojectpuc.model.PathResult
import java.util.*

class Dijkstra(private val graph: Array<IntArray>) {

    private val numVertices: Int = graph.size

    // Função para encontrar o caminho mais curto usando o algoritmo de Dijkstra
    fun findShortestPath(source: Int, destination: Int) : PathResult {
        val distances = IntArray(numVertices) { Int.MAX_VALUE }
        val visited = BooleanArray(numVertices)
        val previous = IntArray(numVertices) { -1 }

        distances[source] = 0

        for (i in 0 until numVertices - 1) {
            val minDistanceVertex = getMinDistanceVertex(distances, visited)
            visited[minDistanceVertex] = true

            for (v in 0 until numVertices) {
                if (!visited[v] && graph[minDistanceVertex][v] != 0 && distances[minDistanceVertex] != Int.MAX_VALUE) {
                    val newDistance = distances[minDistanceVertex] + graph[minDistanceVertex][v]
                    if (newDistance < distances[v]) {
                        distances[v] = newDistance
                        previous[v] = minDistanceVertex
                    }
                }
            }
        }
        return PathResult(
            path = getPath(source, destination, previous),
            estimatedTime = distances[destination]
        )
    }


    // Função auxiliar para encontrar o vértice não visitado com a menor distância
    private fun getMinDistanceVertex(distances: IntArray, visited: BooleanArray): Int {
        var minDistance = Int.MAX_VALUE
        var minDistanceVertex = -1

        for (v in 0 until numVertices) {
            if (!visited[v] && distances[v] <= minDistance) {
                minDistance = distances[v]
                minDistanceVertex = v
            }
        }

        return minDistanceVertex
    }


    // Função auxiliar para obter o caminho mais curto a partir dos vértices anteriores
    private fun getPath(source: Int, destination: Int, previous: IntArray): List<Int> {
        val path = LinkedList<Int>()
        var current = destination

        while (current != -1) {
            path.addFirst(current)
            current = previous[current]
        }

        return path
    }
}
