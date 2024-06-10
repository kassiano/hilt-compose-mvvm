package br.com.kassiano.app.featurepullrequests

import br.com.kassiano.app.domain.model.PullRequest

data class PullRequestsState(
    val items: List<PullRequest>
)