package br.com.kassiano.app

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.kassiano.app.featurehome.HomeScreen
import br.com.kassiano.app.featurepullrequests.PullRequestsScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("pullRequests/{owner}/{repo}") {
            val owner = it.arguments?.getString("owner")
            val repo = it.arguments?.getString("repo")
            PullRequestsScreen(navController = navController)
        }
    }
}