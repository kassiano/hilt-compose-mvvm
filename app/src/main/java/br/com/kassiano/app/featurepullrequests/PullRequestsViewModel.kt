package br.com.kassiano.app.featurepullrequests

import androidx.lifecycle.SavedStateHandle
import br.com.kassiano.app.domain.model.PullRequest
import br.com.kassiano.app.domain.state.PaginatedState
import br.com.kassiano.app.domain.viewmodel.PaginatedViewModel
import br.com.kassiano.app.featurepullrequests.usecase.GetPullRequestsByRepository
import br.com.kassiano.app.featurepullrequests.usecase.GetPullRequestsParams
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class PullRequestsViewModel @Inject constructor(
    dispatcher: CoroutineDispatcher,
    private val getPullRequestsByRepository: GetPullRequestsByRepository,
    private val savedStateHandle: SavedStateHandle
) : PaginatedViewModel<PaginatedState<PullRequest>>(dispatcher) {

    override suspend fun onLoadingMore() {
        getCurrentValue()?.copy(isLoading = true)?.let {
            emitSuccess(it)
        }
    }

    override suspend fun onLoadedMore(value: PaginatedState<PullRequest>) {
        val currentItems = getCurrentValue()?.items.orEmpty().toMutableList()
        currentItems.addAll(value.items)

        val newState = PaginatedState(
            items = currentItems,
            isLoading = false
        )
        emitSuccess(newState)
    }

    override suspend fun fetch(): PaginatedState<PullRequest> {
        val owner = savedStateHandle.get<String>("owner")
        val repo = savedStateHandle.get<String>("repo")
        val result = getPullRequestsByRepository.invoke(
            GetPullRequestsParams(
                owner.orEmpty(),
                repo.orEmpty()
            )
        )
        return PaginatedState(items = result, isLoading = false)
    }
}