package com.example.scavengerar

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.scavengerar.ui.theme.BlueTheme
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding

@HiltAndroidApp
class ScavArApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun getWorkManagerConfiguration() =
                 Configuration.Builder()
                              .setWorkerFactory(workerFactory)
                              .build()
}


@Composable
fun ScavArApplication(finishActivity: () -> Unit) {
    ProvideWindowInsets {
        BlueTheme {
            val navController = rememberNavController()
            Scaffold(
                topBar = { TopBar() },
                backgroundColor = MaterialTheme.colors.background,
                bottomBar = { BottomBar(navController = navController) }
            ) { innerPaddingModifier ->
                NavGraph(
                    finishActivity = finishActivity,
                    navController = navController,
                    modifier = Modifier.padding(innerPaddingModifier)
                )
            }
        }
    }
}


@Composable
fun TopBar() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().statusBarsPadding().background(MaterialTheme.colors.surface)
    ) {
        Text(
            text = "ScavAR",
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.primary,
            modifier = Modifier.padding(16.dp)
        )
        IconButton(
            modifier = Modifier.padding(8.dp),
            onClick = { /* todo */ }
        ) {
            Icon(
                imageVector = Icons.Filled.Settings,
                contentDescription = stringResource(R.string.label_settings),
                tint = MaterialTheme.colors.secondary,
            )
        }
    }
}


@Composable
fun BottomBar(navController: NavController) {
    BottomNavigation(
        Modifier.navigationBarsHeight(additional = 56.dp),
        backgroundColor = MaterialTheme.colors.surface,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        screens.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(screen.icon, contentDescription = stringResource(screen.title)) },
                label = { Text(stringResource(screen.title)) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                alwaysShowLabel = false,
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = MaterialTheme.colors.secondary,
                modifier = Modifier.navigationBarsPadding()
            )
        }
    }
}
