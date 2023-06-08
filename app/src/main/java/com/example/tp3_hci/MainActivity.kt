package com.example.tp3_hci

import android.os.Bundle
import android.widget.EditText
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tp3_hci.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var textField: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        textField = findViewById(R.id.tex)

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_devices, R.id.navigation_routines,  R.id.navigation_user
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val pageName = getPageName(destination.id)
            textField.setText(pageName)
        }
    }

    private fun getPageName(destinationId: Int): String {
        return when (destinationId) {
            R.id.navigation_home -> "Inicio"
            R.id.navigation_devices -> "Dispositivos"
            R.id.navigation_routines -> "Rutinas"
            R.id.navigation_user -> "Usuario"
            // Add cases for other destinations as needed
            else -> ""
        }
    }
}