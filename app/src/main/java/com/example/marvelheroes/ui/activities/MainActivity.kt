package com.example.marvelheroes.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.marvelheroes.R
import com.example.marvelheroes.common.Constants
import com.example.marvelheroes.databinding.ActivityMainBinding
import com.example.marvelheroes.ui.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.security.MessageDigest
import kotlin.text.Charsets.UTF_8

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getCharactersByName("Spider man")
    }

    fun setUpObservers() {
        viewModel.characterResponse.observe(this) {
            Log.d("MAIN", it.toString())
        }
    }

}