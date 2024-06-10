package br.com.kassiano.app.domain.repository

import br.com.kassiano.app.datasource.model.PullRequestResponse
import br.com.kassiano.app.datasource.model.RepoResponse
import br.com.kassiano.app.datasource.services.GithubService
import br.com.kassiano.app.datasource.repository.GitHubRepository

class GitHubRepositoryImpl(
    private val service: GithubService
): GitHubRepository {
    override suspend fun getRepositoriesByLanguage(
        language: String,
        sort: String,
        order: String,
        page: Int
    ): RepoResponse {
        return service.search(query = "language:$language", sort = sort, order = order, page = page)
    }

    override suspend fun getPullRequestsByRepository(
        owner: String,
        repo: String,
        state: String
    ): List<PullRequestResponse> {
        return service.pullRequests(owner, repo, state)
    }
}