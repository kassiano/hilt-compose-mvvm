package br.com.kassiano.app.domain.model

data class Repository(
    val id: Int?,
    val name: String?,
    val description: String?,
    val forksCount: Int?,
    val stargazersCount: Int?,
    val owner: String
)