package com.example.contactosapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.contactosapp.ui.navigation.AppNavigation
import com.example.contactosapp.ui.theme.ContactosAPPTheme
import com.example.contactosapp.viewmodel.ContactViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ContactosAPPTheme {
                val navController = rememberNavController()
                val viewModel: ContactViewModel = viewModel()
                AppNavigation(navController = navController, viewModel = viewModel)
            }
        }
    }
}
