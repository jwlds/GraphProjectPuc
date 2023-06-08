package com.example.graphprojectpuc.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.graphprojectpuc.R
import com.example.graphprojectpuc.database.FirebaseHelper
import com.example.graphprojectpuc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        supportFragmentManager.findFragmentById(binding.contentMain.navHostFragment.id) as NavHostFragment
    }


}