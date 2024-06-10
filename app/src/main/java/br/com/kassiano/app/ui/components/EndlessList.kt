package br.com.kassiano.app.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import br.com.kassiano.app.domain.state.PaginatedState
import br.com.kassiano.app.domain.viewmodel.PaginatedViewModel


@Composable
fun <T> EndlessList(
    state: PaginatedState<T>?,
    viewModel: PaginatedViewModel<PaginatedState<T>>,
    itemListBuilder: @Composable (T) -> Unit,
) {
    val listState = rememberLazyListState()

    val items = state?.items.orEmpty()

    val reachedBottom: Boolean by remember {
        derivedStateOf {
            val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
            lastVisibleItem?.index != 0 && lastVisibleItem?.index == listState.layoutInfo.totalItemsCount - 2
        }
    }

    LaunchedEffect(reachedBottom) {
        if (reachedBottom) viewModel.loadMore()
    }

    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize()
    ) {
        items(items) {
            itemListBuilder(it)
        }

        if (state?.isLoading == true) {
            item {
                LoadingComponent()
            }
        }
    }
}