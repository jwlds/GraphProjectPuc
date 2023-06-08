package com.example.graphprojectpuc.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.example.graphprojectpuc.R
import com.example.graphprojectpuc.algorithms.Dijkstra
import com.example.graphprojectpuc.database.FirebaseHelper
import com.example.graphprojectpuc.databinding.FragmentHomeBinding
import com.example.graphprojectpuc.ui.dialog.ViewMapDialog
import com.example.graphprojectpuc.utils.Utils
import com.example.graphprojectpuc.utils.Utils.formatResult
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
        // Função para criar os prédios da puc campinas
       // Utils.createBuildings()


        val buildingViewModel = ViewModelProvider(this).get(BuildingViewModel::class.java)
        buildingViewModel.buildingList.observe(viewLifecycleOwner) { buildings ->


            val buildingNames = buildings.map { it.name }

            buildingMap = buildings.associate { it.name to it.index } as Map<String, Int>

            val adapter = ArrayAdapter(
                requireContext(),
                R.layout.drop_down_item,
                buildingNames
            )
            binding.selectHomeBuilding.setAdapter(adapter)
            binding.selectDestinationBuilding.setAdapter(adapter)


            dijkstra = Dijkstra(Utils.createMatrix(buildings))



        }

        binding.btnFind.setOnClickListener {
            if (isValid()) {
                val getIndexSelectHomeBuilding =
                    buildingMap[binding.selectHomeBuilding.text.trim().toString()]

                val getIndexSelectDestinationBuilding =
                    buildingMap[binding.selectDestinationBuilding.text.trim().toString()]

                val dijkstraResult = dijkstra.findShortestPath(
                    source = getIndexSelectHomeBuilding!!.toInt(),
                    destination = getIndexSelectDestinationBuilding!!.toInt()
                )

                binding.resultText.text = formatResult(
                    path = getBuildingNamesByIds(dijkstraResult.path),
                    time = dijkstraResult.estimatedTime)
            }
        }

        binding.btnViewMap.setOnClickListener{
            showDialogViewMap()
        }
    }


    private fun isValid(): Boolean {
        val selectedHomeBuilding = binding.selectHomeBuilding.text.trim().toString()
        val selectedDestinationBuilding = binding.selectDestinationBuilding.text.trim().toString()
        if(selectedHomeBuilding.isEmpty()){
            binding.selectHomeBuilding.error = "Escolha um prédio"
            binding.selectHomeBuilding.requestFocus()
            return false
        }
        if(selectedDestinationBuilding.isEmpty()) {
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
