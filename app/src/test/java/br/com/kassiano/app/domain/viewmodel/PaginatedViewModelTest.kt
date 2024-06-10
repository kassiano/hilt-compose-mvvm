package br.com.kassiano.app.domain.viewmodel

import app.cash.turbine.test
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.*

import org.junit.Test

class PaginatedViewModelTest {

    @Test
    fun `When loading more Then it should update current page`() = runBlocking {
        val viewModel = object : PaginatedViewModel<String>(UnconfinedTestDispatcher()) {
            override suspend fun onLoadingMore() {
            }

            override suspend fun onLoadedMore(value: String) {
            }

            override suspend fun fetch(): String {
                return  "value"
            }
        }

        val page = viewModel.currentPage
        val nextPage = page + 1
        viewModel.loadMore()

        viewModel.stateFlow.test {
            assertEquals(Result.success("value"), awaitItem())
        }

        assertEquals(viewModel.currentPage, nextPage)
    }
}