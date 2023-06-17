package com.example.graphprojectpuc.ui.dialog

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.graphprojectpuc.databinding.ViewMapItemBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

// Classe que herda de "BottomSheetDialogFragment" e infla o layout "view_map_item" para exibir o mapa da PUC
class ViewMapDialog() : BottomSheetDialogFragment() {

    private var _binding: ViewMapItemBinding? = null
    private val binding get() = _binding!!



    @SuppressLint("UseGetLayoutInflater")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = ViewMapItemBinding.inflate(LayoutInflater.from(requireContext()))
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}