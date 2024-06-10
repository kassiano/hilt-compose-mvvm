package br.com.kassiano.app.domain.viewmodel

import app.cash.turbine.test
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.*

import org.junit.Test

class FetchViewModelTest {

    @Test
    fun `When initialize Then it should call fetch and have a success result`() = runBlocking {
        val viewModel = object : FetchViewModel<String>(UnconfinedTestDispatcher()) {
            override suspend fun fetch(): String {
              return  "value"
            }
        }

        viewModel.stateFlow.test {
            assertEquals(Result.success("value"), awaitItem())
        }
    }

}