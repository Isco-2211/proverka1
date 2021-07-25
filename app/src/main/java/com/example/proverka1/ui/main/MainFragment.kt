package com.example.proverka1.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.proverka1.R
import com.example.proverka1.data.Settings
import com.example.proverka1.databinding.FragmentMainBinding
import org.koin.android.ext.android.inject

class MainFragment:Fragment(R.layout.fragment_main) {

    private val settings: Settings by inject()
    private lateinit var navController: NavController
    private lateinit var binding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        settings.signedIn = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainer)
        binding.bnv.setupWithNavController(navController)
    }

}