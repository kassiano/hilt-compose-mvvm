package br.com.kassiano.app.domain.model

data class PullRequest(
    val id: Int,
    val title: String,
    val htmlUrl: String,
    val user: String,
    val avatarUrl: String
)
