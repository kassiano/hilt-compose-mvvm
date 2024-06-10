package br.com.kassiano.app.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class FetchViewModel<T>(
    protected val dispatcher: CoroutineDispatcher
): ViewModel() {

    private val _stateFlow = MutableStateFlow<Result<T>?>(value = null)
    val stateFlow: StateFlow<Result<T>?> = _stateFlow

    init {
        viewModelScope.launch(dispatcher) {
            try {

                val value = fetch()
                emitSuccess(value)

            } catch (t: Throwable) {
                emitFailure(t)
            }
        }
    }

    abstract suspend fun fetch(): T

    suspend fun retry() {
        fetch()
    }

    protected suspend fun emitSuccess(value: T) {
        _stateFlow.emit(Result.success(value))
    }

    protected suspend fun emitFailure(throwable: Throwable) {
        _stateFlow.emit(Result.failure(throwable))
    }

    protected fun getCurrentValue(): T? = _stateFlow.value?.getOrNull()
}