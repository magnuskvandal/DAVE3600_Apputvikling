package com.example.bursdagsassistent_s356228.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.bursdagsassistent_s356228.ui.screens.FriendDetailsScreen
import com.example.bursdagsassistent_s356228.ui.screens.FriendListScreen
import com.example.bursdagsassistent_s356228.ui.screens.PreferencesScreen

object AppDestinations {
    const val FRIEND_LIST = "friend_list"
    const val FRIEND_DETAILS = "friend_details"
    const val FRIEND_FORM = "friend_form"
    const val FRIEND_ID = "friendId"
    const val PREFERENCES = "preferences"
}

@Composable
fun AppNavigation(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = AppDestinations.FRIEND_LIST
    ){
        composable(route = AppDestinations.FRIEND_LIST) {
            FriendListScreen(
                onFriendClick = { friendId ->
                    navController.navigate(route = "${AppDestinations.FRIEND_DETAILS}/$friendId")
                },
                onAddFriendClick = { /* Kommer senere */ },
                onPreferencesClick = {
                    navController.navigate(AppDestinations.PREFERENCES) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }

        composable(route = AppDestinations.PREFERENCES) {
            PreferencesScreen(
                onFriendListClick = {
                    navController.navigate(AppDestinations.FRIEND_LIST) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }

        composable(
            route = "${AppDestinations.FRIEND_DETAILS}/{${AppDestinations.FRIEND_ID}}",
            arguments = listOf(navArgument(AppDestinations.FRIEND_ID) { type = NavType.IntType })
        ) {
            FriendDetailsScreen(
                onNavigate = { navController.popBackStack() },
                onEditClick = { /* Kommer senere */ }
            )
        }
    }
}
