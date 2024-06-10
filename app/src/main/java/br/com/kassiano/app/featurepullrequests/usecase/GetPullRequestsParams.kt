package br.com.kassiano.app.featurepullrequests.usecase

data class GetPullRequestsParams(
    val repoOwner: String,
    val repoName: String
)