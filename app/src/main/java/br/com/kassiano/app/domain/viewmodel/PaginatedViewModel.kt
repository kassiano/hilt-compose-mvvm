package br.com.kassiano.app.domain.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

abstract class PaginatedViewModel<T>(
    dispatcher: CoroutineDispatcher
): FetchViewModel<T>(dispatcher) {

    var currentPage = 1
        private set
    abstract suspend fun onLoadingMore()
    abstract suspend fun onLoadedMore(value: T)

    fun loadMore() {
        viewModelScope.launch(dispatcher) {
            onLoadingMore()
            try {
                currentPage ++
                val value = fetch()
                onLoadedMore(value)
            } catch (t: Throwable) {
                emitFailure(t)
            }
        }
    }
}