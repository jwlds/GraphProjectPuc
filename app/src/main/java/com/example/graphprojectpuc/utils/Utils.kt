package com.example.graphprojectpuc.utils

import com.example.graphprojectpuc.database.FirebaseHelper
import com.example.graphprojectpuc.model.Building

object Utils {


    fun createMatrix(buildings: List<Building>): Array<IntArray> {
        val size = buildings.size
        val matrix = Array(size) { IntArray(size) }

        val buildingMap = mutableMapOf<String, Int>()
        for (i in buildings.indices) {
            buildingMap[buildings[i].name!!] = i
        }

        for (i in buildings.indices) {
            val building = buildings[i]
            val connectedBuildings = building.connectedBuildings
            if (connectedBuildings != null) {
                for (connectedBuilding in connectedBuildings) {
                    val j = buildingMap[connectedBuilding.name!!] ?: continue
                    val weight = connectedBuilding.weight ?: continue
                    matrix[i][j] = weight
                }
            }
        }

        return matrix
    }

    fun formatResult(path: String, time: Int): String {
        return "O caminho mais rápido é $path e o tempo estimado é $time min."
    }

     fun createBuildings() {
        val db = FirebaseHelper.getDatabase()
        val buildingsRef = db.collection("buildings")

        val buildingDataList = listOf(
            hashMapOf(
                "index" to 0,
                "name" to "H15",
                "description" to "Prédio de Aulas da Poli",
                "connectedBuildings" to listOf(
                    hashMapOf("name" to "REITORIA", "weight" to 9),
                    hashMapOf("name" to "H13", "weight" to 1),
                    hashMapOf("name" to "REFEITÓRIO", "weight" to 3),
                    hashMapOf("name" to "CT-BA", "weight" to 2)
                )
            ),
            hashMapOf(
                "index" to 1,
                "name" to "CT_BA",
                "description" to " Prédio de Aulas da Poli",
                "connectedBuildings" to listOf(
                    hashMapOf("name" to "CT-B1", "weight" to 2),
                    hashMapOf("name" to "REFEITÓRIO", "weight" to 3),
                    hashMapOf("name" to "H15", "weight" to 2),
                    hashMapOf("name" to "AGÊNCIAS_BANCÁRIAS", "weight" to 3),
                    hashMapOf("name" to "AUDITÓRIO", "weight" to 2)
                ),

                ),
            hashMapOf(
                "index" to 2,
                "name" to "AGÊNCIAS_BANCÁRIAS",
                "description" to "Agências Bancárias",
                "connectedBuildings" to listOf(
                    hashMapOf("name" to "CT-BA", "weight" to 2) ,
                    hashMapOf("name" to "AUDITÓRIO", "weight" to 2)
                ),
            ),
            hashMapOf(
                "index" to 3,
                "name" to "AUDITÓRIO",
                "description" to "Auditório Dom Gilberto",
                "connectedBuildings" to listOf(
                    hashMapOf("name" to "CT-BA", "weight" to 2) ,
                    hashMapOf("name" to "AGÊNCIAS_BANCÁRIAS", "weight" to 2)
                ),
            ),
            hashMapOf(
                "index" to 4,
                "name" to "CT_B1",
                "description" to "CT2",
                "connectedBuildings" to listOf(
                    hashMapOf("name" to "CT_BA", "weight" to 2) ,
                    hashMapOf("name" to "REFEITÓRIO", "weight" to 2) ,
                    hashMapOf("name" to "CT_C1", "weight" to 2),
                    hashMapOf("name" to "CT_B2", "weight" to 2)
                ),
            ),
            hashMapOf(
                "index" to 5,
                "name" to "CT_B2",
                "description" to "CT2",
                "connectedBuildings" to listOf(
                    hashMapOf("name" to "CT_B1", "weight" to 2) ,
                    hashMapOf("name" to "CT_C2", "weight" to 2) ,
                ),
            ),
            hashMapOf(
                "index" to 6,
                "name" to "CT_C2",
                "description" to "CT2",
                "connectedBuildings" to listOf(
                    hashMapOf("name" to "CT_B2", "weight" to 2) ,
                    hashMapOf("name" to "CT_C1", "weight" to 2) ,
                ),
            ),
            hashMapOf(
                "index" to 7,
                "name" to "CT_C1",
                "description" to "CT2",
                "connectedBuildings" to listOf(
                    hashMapOf("name" to "CT_B1", "weight" to 2) ,
                    hashMapOf("name" to "H14", "weight" to 2) ,
                    hashMapOf("name" to "CT_C2", "weight" to 2) ,
                ),
            ),
            hashMapOf(
                "index" to 8,
                "name" to "H14",
                "description" to "Prédio de Arquitetura",
                "connectedBuildings" to listOf(
                    hashMapOf("name" to "CT_C1", "weight" to 2) ,
                    hashMapOf("name" to "H12", "weight" to 2) ,
                ),
            ),
            hashMapOf(
                "index" to 9,
                "name" to "H12",
                "description" to "Secretaria da Politécnica",
                "connectedBuildings" to listOf(
                    hashMapOf("name" to "H14", "weight" to 2) ,
                    hashMapOf("name" to "H10", "weight" to 2) ,
                ),
            ),
            hashMapOf(
                "index" to 10,
                "name" to "H10",
                "description" to "Novo Mescla",
                "connectedBuildings" to listOf(
                    hashMapOf("name" to "H12", "weight" to 2) ,
                    hashMapOf("name" to "H12", "weight" to 2) ,
                    hashMapOf("name" to "CAPELA", "weight" to 2) ,
                ),
            ),
            hashMapOf(
                "index" to 11,
                "name" to "H8",
                "description" to "Prédio de Aulas da EcoN",
                "connectedBuildings" to listOf(
                    hashMapOf("name" to "H10", "weight" to 2) ,
                    hashMapOf("name" to " EcoN ", "weight" to 2) ,
                    hashMapOf("name" to "H6", "weight" to 2) ,
                    hashMapOf("name" to "CAPELA", "weight" to 2) ,
                ),
            ),
            hashMapOf(
                "index" to 12,
                "name" to "H6",
                "description" to " Prédio de Aulas da EcoN",
                "connectedBuildings" to listOf(
                    hashMapOf("name" to "H8", "weight" to 2) ,
                    hashMapOf("name" to "EcoN", "weight" to 2) ,
                    hashMapOf("name" to "H4", "weight" to 2) ,
                    hashMapOf("name" to "CAPELA", "weight" to 2) ,
                ),
            ),
            hashMapOf(
                "index" to 13,
                "name" to "H4",
                "description" to " Prédio de Aulas da EcoN",
                "connectedBuildings" to listOf(
                    hashMapOf("name" to "H6", "weight" to 2) ,
                    hashMapOf("name" to "EcoN", "weight" to 2) ,
                    hashMapOf("name" to "H2", "weight" to 2) ,
                    hashMapOf("name" to "CAPELA", "weight" to 2) ,
                    hashMapOf("name" to "H0", "weight" to 2) ,
                ),
            ),
            hashMapOf(
                "index" to 14,
                "name" to "EcoN",
                "description" to "Secretaria do EcoN",
                "connectedBuildings" to listOf(
                    hashMapOf("name" to "H6", "weight" to 2) ,
                    hashMapOf("name" to "H4", "weight" to 2) ,
                    hashMapOf("name" to "H8", "weight" to 2) ,
                ),
            ),
            hashMapOf(
                "index" to 15,
                "name" to "H2",
                "description" to "Prédio de Aulas da EcoN",
                "connectedBuildings" to listOf(
                    hashMapOf("name" to "H4", "weight" to 2) ,
                    hashMapOf("name" to "H0", "weight" to 2) ,
                    hashMapOf("name" to "BLOCO_A", "weight" to 2) ,
                ),
            ),
            hashMapOf(
                "index" to 16,
                "name" to "H0",
                "description" to "Prédio de Segurança",
                "connectedBuildings" to listOf(
                    hashMapOf("name" to "H2", "weight" to 2) ,
                    hashMapOf("name" to "H4", "weight" to 2) ,
                    hashMapOf("name" to "CAPELA", "weight" to 2) ,
                    hashMapOf("name" to "H7", "weight" to 2) ,
                    hashMapOf("name" to "H5", "weight" to 2) ,
                    hashMapOf("name" to "H3", "weight" to 2) ,
                    hashMapOf("name" to "H1", "weight" to 2) ,
                ),
            ),
            hashMapOf(
                "index" to 17,
                "name" to "CAPELA",
                "description" to "Capela",
                "connectedBuildings" to listOf(
                    hashMapOf("name" to "H10", "weight" to 2) ,
                    hashMapOf("name" to "H8", "weight" to 2) ,
                    hashMapOf("name" to "H6", "weight" to 2) ,
                    hashMapOf("name" to "H4", "weight" to 2) ,
                    hashMapOf("name" to "H0", "weight" to 2) ,
                    hashMapOf("name" to "H7", "weight" to 2) ,
                    hashMapOf("name" to "H9", "weight" to 2) ,
                    hashMapOf("name" to "REFETÓRIO", "weight" to 2) ,
                    hashMapOf("name" to "H11", "weight" to 2) ,
                ),
            ),
            hashMapOf(
                "index" to 18,
                "name" to "H1",
                "description" to "Prédio de Aulas ELC",
                "connectedBuildings" to listOf(
                    hashMapOf("name" to "H0", "weight" to 2) ,
                    hashMapOf("name" to "H4", "weight" to 2) ,
                    hashMapOf("name" to "BLOCO_A", "weight" to 2) ,
                ),
            ),
            hashMapOf(
                "index" to 19,
                "name" to "H3",
                "description" to "Prédio de Aulas ELC",
                "connectedBuildings" to listOf(
                    hashMapOf("name" to "H1", "weight" to 2) ,
                    hashMapOf("name" to "H5", "weight" to 2) ,
                    hashMapOf("name" to "ELC", "weight" to 2) ,
                ),
            ),
            hashMapOf(
                "index" to 20,
                "name" to "H5",
                "description" to "Prédio de Aulas ELC",
                "connectedBuildings" to listOf(
                    hashMapOf("name" to "H3", "weight" to 2) ,
                    hashMapOf("name" to "H0", "weight" to 2) ,
                    hashMapOf("name" to "H7", "weight" to 2) ,
                    hashMapOf("name" to "ELC", "weight" to 2) ,
                ),
            ),
            hashMapOf(
                "index" to 21,
                "name" to "H7",
                "description" to "Prédio de Aulas ELC",
                "connectedBuildings" to listOf(
                    hashMapOf("name" to "H5", "weight" to 2) ,
                    hashMapOf("name" to "H0", "weight" to 2) ,
                    hashMapOf("name" to "CAPELA", "weight" to 2) ,
                    hashMapOf("name" to "ELC", "weight" to 2) ,
                    hashMapOf("name" to "H9", "weight" to 2) ,
                ),
            ),
            hashMapOf(
                "index" to 22,
                "name" to "H9",
                "description" to "Prédio de Laboratório",
                "connectedBuildings" to listOf(
                    hashMapOf("name" to "H7", "weight" to 2) ,
                    hashMapOf("name" to "H11", "weight" to 2) ,
                    hashMapOf("name" to "CAPELA", "weight" to 2) ,
                    hashMapOf("name" to "ELC", "weight" to 2) ,
                ),
            ),
            hashMapOf(
                "index" to 23,
                "name" to "H11",
                "description" to "CAA e MESCLA",
                "connectedBuildings" to listOf(
                    hashMapOf("name" to "CAPELA", "weight" to 2) ,
                    hashMapOf("name" to "REFEITÓRIO", "weight" to 2) ,
                    hashMapOf("name" to "H13", "weight" to 2) ,
                ),
            ),
            hashMapOf(
                "index" to 24,
                "name" to "H13",
                "description" to "Biblioteca e Escritorio de Talentos",
                "connectedBuildings" to listOf(
                    hashMapOf("name" to "H11", "weight" to 2) ,
                    hashMapOf("name" to "REFEITÓRIO", "weight" to 2) ,
                    hashMapOf("name" to "H15", "weight" to 2) ,
                    hashMapOf("name" to "REITORIA", "weight" to 2) ,
                ),
            ),
            hashMapOf(
                "index" to 25,
                "name" to "REFEITÓRIO",
                "description" to "Praça de Alimentação",
                "connectedBuildings" to listOf(
                    hashMapOf("name" to "H11", "weight" to 2) ,
                    hashMapOf("name" to "CAPELA", "weight" to 2) ,
                    hashMapOf("name" to "H15", "weight" to 2) ,
                    hashMapOf("name" to "H13", "weight" to 2) ,
                    hashMapOf("name" to "CT_BA", "weight" to 2) ,
                    hashMapOf("name" to "CT_B1", "weight" to 2) ,
                ),
            ),

            hashMapOf(
                "index" to 26,
                "name" to "REITORIA",
                "description" to "Prédio da Reitoria",
                "connectedBuildings" to listOf(
                    hashMapOf("name" to "H15", "weight" to 2) ,
                    hashMapOf("name" to "H13", "weight" to 2) ,
                    hashMapOf("name" to "H9", "weight" to 2) ,
                    hashMapOf("name" to "ELC", "weight" to 2) ,
                ),
            ),

            hashMapOf(
                "index" to 27,
                "name" to "ELC",
                "description" to "Secretaria do ELC",
                "connectedBuildings" to listOf(
                    hashMapOf("name" to "H7", "weight" to 2) ,
                    hashMapOf("name" to "H9", "weight" to 2) ,
                    hashMapOf("name" to "H5", "weight" to 2) ,
                    hashMapOf("name" to "H3", "weight" to 2) ,
                    hashMapOf("name" to "BLOCO_A", "weight" to 2) ,
                ),
            ),
            hashMapOf(
                "index" to 28,
                "name" to "Bloco_A",
                "description" to "Bloco A",
                "connectedBuildings" to listOf(
                    hashMapOf("name" to "H2", "weight" to 2) ,
                    hashMapOf("name" to " EcoN ", "weight" to 2) ,
                    hashMapOf("name" to "H1", "weight" to 2) ,
                    hashMapOf("name" to "H3", "weight" to 2) ,
                    hashMapOf("name" to "ELC", "weight" to 2) ,
                ),
            ),

            hashMapOf(
                "index" to 29,
                "name" to "BLOCO_C",
                "description" to "Bloco C",
                "connectedBuildings" to listOf(
                    hashMapOf("name" to "H13", "weight" to 2) ,
                    hashMapOf("name" to "BLOCO_A", "weight" to 2) ,
                ),
            ),

            hashMapOf(
                "index" to 30,
                "name" to "Biblioteca_2",
                "description" to "Biblioteca 2",
                "connectedBuildings" to listOf(
                    hashMapOf("name" to "H13", "weight" to 2) ,
                    hashMapOf("name" to "BLOCO_C", "weight" to 2) ,
                    hashMapOf("name" to "BLOCO_D", "weight" to 2) ,
                ),
            ),

            hashMapOf(
                "index" to 31,
                "name" to "BLOCO_D",
                "description" to "Bloco D",
                "connectedBuildings" to listOf(
                    hashMapOf("name" to "BLOCO_A", "weight" to 2) ,
                    hashMapOf("name" to "BLOCO_E", "weight" to 2) ,
                ),
            ),

            hashMapOf(
                "index" to 32,
                "name" to "BLOCO_E",
                "description" to "Bloco E",
                "connectedBuildings" to listOf(
                    hashMapOf("name" to "BLOCO_D", "weight" to 2) ,
                    hashMapOf("name" to "BLOCO_G", "weight" to 2) ,
                    hashMapOf("name" to "BLOCO_F", "weight" to 2) ,
                ),
            ),
            hashMapOf(
                "index" to 33,
                "name" to "BLOCO_F",
                "description" to "Bloco F",
                "connectedBuildings" to listOf(
                    hashMapOf("name" to "BLOCO_E", "weight" to 2) ,
                ),
            ),
            hashMapOf(
                "index" to 34,
                "name" to "BLOCO_G",
                "description" to "Bloco G",
                "connectedBuildings" to listOf(
                    hashMapOf("name" to "BLOCO_E", "weight" to 2) ,
                    hashMapOf("name" to "BLOCO_H", "weight" to 2) ,
                ),
            ),
            hashMapOf(
                "index" to 35,
                "name" to "BLOCO_H",
                "description" to "Bloco H",
                "connectedBuildings" to listOf(
                    hashMapOf("name" to "BLOCO_G", "weight" to 2) ,
                ),
            ),
        )

        for (buildingData in buildingDataList) {
            buildingsRef.add(buildingData)
                .addOnSuccessListener { documentReference ->

                }
                .addOnFailureListener { e ->

                }
        }
    }
}