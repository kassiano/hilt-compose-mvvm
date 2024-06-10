package br.com.kassiano.app.datasource.repository
import br.com.kassiano.app.datasource.model.PullRequestResponse
import br.com.kassiano.app.datasource.model.RepoResponse

interface GitHubRepository {
    suspend fun getRepositoriesByLanguage(language: String, sort: String, order: String, page: Int): RepoResponse
    suspend fun getPullRequestsByRepository(owner: String, repo: String, state: String): List<PullRequestResponse>
}