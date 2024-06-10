package br.com.kassiano.app.featurepullrequests

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import br.com.kassiano.app.ui.components.EndlessList
import br.com.kassiano.app.ui.components.ErrorComponent
import br.com.kassiano.app.ui.components.ListTile
import br.com.kassiano.app.ui.components.LoadingComponent
import br.com.kassiano.app.ui.components.UiStateComponent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PullRequestsScreen(
    navController: NavHostController,
    viewModel: PullRequestsViewModel = hiltViewModel()
) {
    val state = viewModel.stateFlow.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Pull Requests") })
        },
        content = {
            Box {
                Column {
                    UiStateComponent(
                        stateValue = state.value,
                        onSuccess = {

                            EndlessList(
                                state = it,
                                viewModel = viewModel,
                                itemListBuilder = {
                                    ListTile(
                                        title = it.title,
                                        subtitle = it.user,
                                    )
                                }
                            )
                        },
                        onError = { ErrorComponent() },
                        onLoading = { LoadingComponent() }
                    )
                }
            }
        }
    )
}
