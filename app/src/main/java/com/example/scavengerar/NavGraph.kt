package com.example.scavengerar

import androidx.activity.compose.BackHandler
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.TableRows
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.scavengerar.ui.ItemsScreen
import com.example.scavengerar.viewmodels.ItemViewModel

/**
 * Destinations used in the [ScavArApplication].
 */

val AppIcons = Icons.Default

sealed class Screen(val route: String, @StringRes val title: Int, val icon: ImageVector) {
    object Items : Screen("items", R.string.items_screen, AppIcons.TableRows)
    object Scanner : Screen("scanner", R.string.scanner_screen, AppIcons.CameraRear)
    object Levels : Screen("levels", R.string.levels_screen, AppIcons.AccountTree)
}

val screens = listOf(
    Screen.Items,
    Screen.Scanner,
    Screen.Levels,
)

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    finishActivity: () -> Unit = {},
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Items.route,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Items.route) {
            BackHandler {
                finishActivity()
            }
            val itemViewModel = hiltViewModel<ItemViewModel>()
            ItemsScreen(itemViewModel)
        }
        composable(Screen.Levels.route) {
            BackHandler {
                finishActivity()
            }
            val itemViewModel = hiltViewModel<ItemViewModel>()
            ItemsScreen(itemViewModel)
        }
    }
}