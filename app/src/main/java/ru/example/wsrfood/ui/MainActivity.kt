package ru.example.wsrfood.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import ru.example.wsrfood.R
import ru.example.wsrfood.databinding.ActivityMainBinding
import ru.example.wsrfood.extensions.gone
import ru.example.wsrfood.extensions.visible

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
        if (navHostFragment != null) {
            navController = navHostFragment.findNavController()
        }

        binding.bottomNavigationView.setupWithNavController(navController)
    }

    fun showBottomMenu() {
        binding.bottomNavigationView.visible()
    }

    fun hideBottomMenu() {
        binding.bottomNavigationView.gone()
    }
}