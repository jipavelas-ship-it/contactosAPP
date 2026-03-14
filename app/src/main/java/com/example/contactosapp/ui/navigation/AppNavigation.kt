package com.example.contactosapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.contactosapp.ui.screens.AddEditContactScreen
import com.example.contactosapp.ui.screens.ContactListScreen
import com.example.contactosapp.ui.screens.FavoritesScreen
import com.example.contactosapp.viewmodel.ContactViewModel

sealed class Screen(val route: String) {
    object Contacts : Screen("contacts")
    object AddContact : Screen("add_contact")
    object EditContact : Screen("edit_contact/{contactId}") {
        fun createRoute(contactId: Int) = "edit_contact/$contactId"
    }
    object Favorites : Screen("favorites")
}

@Composable
fun AppNavigation(
    navController: NavHostController,
    viewModel: ContactViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Contacts.route
    ) {
        composable(Screen.Contacts.route) {
            ContactListScreen(
                viewModel = viewModel,
                onAddContact = { navController.navigate(Screen.AddContact.route) },
                onEditContact = { contactId ->
                    navController.navigate(Screen.EditContact.createRoute(contactId))
                },
                onFavoritesClick = { navController.navigate(Screen.Favorites.route) }
            )
        }
        composable(Screen.AddContact.route) {
            AddEditContactScreen(
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable(
            route = Screen.EditContact.route,
            arguments = listOf(navArgument("contactId") { type = NavType.IntType })
        ) { backStackEntry ->
            val contactId = backStackEntry.arguments?.getInt("contactId")
            AddEditContactScreen(
                viewModel = viewModel,
                contactId = contactId,
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable(Screen.Favorites.route) {
            FavoritesScreen(
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() },
                onEditContact = { contactId ->
                    navController.navigate(Screen.EditContact.createRoute(contactId))
                }
            )
        }
    }
}
