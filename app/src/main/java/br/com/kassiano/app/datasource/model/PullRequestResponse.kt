package br.com.kassiano.app.datasource.model

import com.google.gson.annotations.SerializedName

data class PullRequestResponse(
    val id: Int,
    val title: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    val user: UserResponse,
    val body: String?
)

data class UserResponse(
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String
)