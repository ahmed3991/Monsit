package com.eloueduniv.monsit.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eloueduniv.monsit.presentation.call.add.AddCallScreen
import com.eloueduniv.monsit.presentation.main.MainScreen
import com.eloueduniv.monsit.presentation.search.SearchScreen

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController()
){
    NavHost(
        navController = navController,
        startDestination = Screen.Main.route
    ){
        composable(route = Screen.Main.route) {
            MainScreen(
                onNavigateToSearch = {
                    navController.navigate(Screen.Search.route)
                },
                onNavigateToContacts = {
                    navController.navigate(Screen.Contacts.route)
                },
                onNavigateToAdd = {
                    navController.navigate(Screen.AddCall.route)
                }
            )
        }

        composable(route = Screen.Search.route) {
            SearchScreen(onBack = {
                navController.popBackStack()
            })
        }

        composable(route = Screen.AddCall.route){
            AddCallScreen(onBack = {
                navController.popBackStack()
            })
        }
    }
}
