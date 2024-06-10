package br.com.kassiano.app.featurehome

import br.com.kassiano.app.domain.model.Repository
import br.com.kassiano.app.domain.state.PaginatedState
import br.com.kassiano.app.domain.viewmodel.PaginatedViewModel
import br.com.kassiano.app.featurehome.usecase.GetJavaRepositoriesByStars
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getJavaRepositoriesByStars: GetJavaRepositoriesByStars,
    dispatcher: CoroutineDispatcher
) : PaginatedViewModel<PaginatedState<Repository>>(dispatcher) {

    override suspend fun fetch(): PaginatedState<Repository> {
        val result = getJavaRepositoriesByStars(currentPage)
        return PaginatedState(
            items = result,
            isLoading = false
        )
    }

    override suspend fun onLoadingMore() {
        getCurrentValue()?.copy(isLoading = true)?.let {
            emitSuccess(it)
        }
    }

    override suspend fun onLoadedMore(value: PaginatedState<Repository>) {
        val currentItems = getCurrentValue()?.items.orEmpty().toMutableList()
        currentItems.addAll(value.items)

        val newState = PaginatedState(
            items = currentItems,
            isLoading = false
        )
        emitSuccess(newState)
    }
}