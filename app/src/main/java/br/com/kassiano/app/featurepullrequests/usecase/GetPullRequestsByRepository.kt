package br.com.kassiano.app.featurepullrequests.usecase

import br.com.kassiano.app.datasource.model.PullRequestResponse
import br.com.kassiano.app.datasource.repository.GitHubRepository
import br.com.kassiano.app.domain.mapper.DomainMapper
import br.com.kassiano.app.domain.model.PullRequest

class GetPullRequestsByRepository(
    private val repository: GitHubRepository,
    private val mapper: DomainMapper<PullRequestResponse, PullRequest>
) {
    suspend fun invoke(params: GetPullRequestsParams): List<PullRequest> {
       val result = repository.getPullRequestsByRepository(
            owner = params.repoOwner,
            repo = params.repoName,
            state = "open"
        )

        return result.map { mapper.toDomain(it) }
    }
}