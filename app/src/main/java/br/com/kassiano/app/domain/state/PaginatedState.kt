package br.com.kassiano.app.domain.state

data class PaginatedState<T>(
    val items: List<T>,
    val isLoading: Boolean
)