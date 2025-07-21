package com.oleg.cbttracker

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.oleg.cbttracker.ui.screens.ThoughtEditScreen
import com.oleg.cbttracker.ui.screens.ThoughtListScreen
import com.oleg.cbttracker.viewmodel.EditViewModel
import com.oleg.cbttracker.viewmodel.ListViewModel
import java.util.UUID

@Composable
fun CbtTrackerApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Destinations.LIST) {

        composable(Destinations.LIST) {
            val vm: ListViewModel = hiltViewModel()
            val entries by vm.entries.collectAsState()
            ThoughtListScreen(
                entries = entries,
                onAdd = { navController.navigate("edit/new") },
                onSelect = { id -> navController.navigate("edit/$id") }
            )
        }

        composable(
            route = Destinations.ROUTE_EDIT,
            arguments = listOf(navArgument(Destinations.ARG_ID) { type = NavType.StringType })
        ) { backStackEntry ->
            val arg = backStackEntry.arguments?.getString(Destinations.ARG_ID) ?: "new"
            val uuid = arg.takeIf { it != "new" }?.let(UUID::fromString)
            val vm: EditViewModel = hiltViewModel()
            ThoughtEditScreen(
                text = vm.text,
                onTextChange = vm::onTextChange,
                onSave = { vm.save { navController.popBackStack() } },
                onDelete = { vm.delete { navController.popBackStack() } },
                isNew = uuid == null
            )
        }
    }
}
