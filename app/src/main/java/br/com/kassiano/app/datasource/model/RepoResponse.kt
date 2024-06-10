package br.com.kassiano.app.datasource.model

import com.google.gson.annotations.SerializedName

data class RepoResponse(
    @SerializedName("total_count")
    val totalCount: Long?,
    val items: List<Repo>?
)

data class Repo(
    val id: Int?,
    val name: String?,
    val description: String?,

    @SerializedName("forks_count")
    val forksCount: Int?,

    @SerializedName("stargazers_count")
    val stargazersCount: Int?,

    val owner: Owner
)

data class Owner(
    val login: String
)

