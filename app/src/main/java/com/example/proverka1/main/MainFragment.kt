package com.example.proverka1.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.proverka1.R
import com.example.proverka1.data.Settings
import org.koin.android.ext.android.inject

class MainFragment:Fragment(R.layout.fragment_main) {

    private val settings: Settings by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        settings.signedIn = true
    }
}