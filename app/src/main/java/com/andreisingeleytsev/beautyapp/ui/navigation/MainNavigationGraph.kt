package com.andreisingeleytsev.beautyapp.ui.navigation


import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.andreisingeleytsev.beautyapp.ui.screens.category_screen.CategoryScreen
import com.andreisingeleytsev.beautyapp.ui.screens.home_screen.HomeScreen
import com.andreisingeleytsev.beautyapp.ui.screens.onboard_screen.OnBoardScreen
import com.andreisingeleytsev.beautyapp.ui.screens.tip_screen.TipScreen
import com.andreisingeleytsev.beautyapp.ui.utils.Routes

@Composable
fun MainNavigationGraph(startScreen: String) {
    val navHostController = rememberNavController()
    val navBackStackEntry: NavBackStackEntry? =
        navHostController.currentBackStackEntryAsState().value
    val savedStateHandle = navBackStackEntry?.savedStateHandle
    NavHost(navController = navHostController, startDestination = startScreen) {
        composable(Routes.HOME_SCREEN) {
            HomeScreen(navHostController)
        }
        composable(Routes.CATEGORY_SCREEN+ "/{category_id}") {backStack->
            CategoryScreen(navHostController)
        }
        composable(Routes.ONBOARD_SCREEN) {
            OnBoardScreen(navHostController)
        }
        composable(Routes.TIP_SCREEN + "/{category_id}/{tip_index}") {backStack->
            val categoryIndex = backStack.arguments?.getString("category_id")?.toInt() ?: -1
            val tipIndex = backStack.arguments?.getString("tip_index")?.toInt() ?: -1
            TipScreen(categoryIndex, tipIndex, navHostController)
        }
    }
}
