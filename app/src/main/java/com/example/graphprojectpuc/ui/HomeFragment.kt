package com.example.graphprojectpuc.ui

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.example.graphprojectpuc.R
import com.example.graphprojectpuc.algorithms.Dijkstra
import com.example.graphprojectpuc.databinding.FragmentHomeBinding
import com.example.graphprojectpuc.ui.dialog.ViewMapDialog
import com.example.graphprojectpuc.utils.Utils
import com.example.graphprojectpuc.viewmodel.BuildingViewModel


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var buildingMap: Map<String, Int>

    private lateinit var dijkstra: Dijkstra


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       // Função para criar os prédios da PUC Campinas
       // Utils.createBuildings()

        // Inicialização do ViewModel para obter a lista de prédios
        val buildingViewModel = ViewModelProvider(this).get(BuildingViewModel::class.java)
        buildingViewModel.buildingList.observe(viewLifecycleOwner) { buildings ->


            // Criação do mapa com os nomes do prédios
            val buildingNames = buildings.map { it.name }

            // Criação do mapa de prédios usando o nome como chave e o índice como valor
            buildingMap = buildings.associate { it.name to it.index } as Map<String, Int>

            // Criação de um adapter para exibir os nomes dos prédios em um AutoCompleteTextView
            val adapter = ArrayAdapter(
                requireContext(),
                R.layout.drop_down_item,
                buildingNames
            )
            binding.selectHomeBuilding.setAdapter(adapter)
            binding.selectDestinationBuilding.setAdapter(adapter)


            // Criação do objeto Dijkstra com a matriz
            dijkstra = Dijkstra(Utils.createMatrix(buildings))



        }

        binding.btnFind.setOnClickListener {
            if (isValid()) {
                val originBuilding = binding.selectHomeBuilding.text.trim().toString()
                val destinationBuilding = binding.selectDestinationBuilding.text.trim().toString()
                val dijkstraResult = dijkstra.findShortestPath(
                    source = buildingMap[originBuilding]!!.toInt(),
                    destination =   buildingMap[destinationBuilding]!!.toInt()
                )
                binding.resultPathText.text = "O caminho mais rápido entre o $originBuilding e $destinationBuilding:\n\n${getBuildingNamesByIds(dijkstraResult.path)}."
                binding.resultTimeText.text = "Tempo estimado: ${dijkstraResult.estimatedTime} min."
            }
        }

        binding.btnViewMap.setOnClickListener{
            showDialogViewMap()
        }
    }


    private fun isValid(): Boolean {
        val selectedHomeBuilding = binding.selectHomeBuilding.text.trim().toString()
        val selectedDestinationBuilding = binding.selectDestinationBuilding.text.trim().toString()
        if (selectedHomeBuilding.isEmpty()) {
            binding.selectHomeBuilding.error = "Escolha um prédio"
            binding.selectHomeBuilding.requestFocus()
            return false
        }
        if (selectedDestinationBuilding.isEmpty()) {
            binding.selectDestinationBuilding.error = "Escolha um prédio"
            binding.selectDestinationBuilding.requestFocus()
            return false
        }
        return true
    }

    fun getBuildingNamesByIds(buildingIds: List<Int>): String {
        val buildingNames = buildingIds.mapNotNull { id ->
            buildingMap.entries.find { it.value == id }?.key
        }
        return buildingNames.joinToString(" -> ")
    }

    private fun showDialogViewMap() {
        val dialog = ViewMapDialog()
        dialog.show(requireActivity().supportFragmentManager, "DialogViewMap")
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
