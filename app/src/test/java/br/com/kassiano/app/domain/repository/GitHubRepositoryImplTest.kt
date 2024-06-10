package br.com.kassiano.app.domain.repository

import br.com.kassiano.app.datasource.model.RepoResponse
import br.com.kassiano.app.datasource.repository.GitHubRepository
import br.com.kassiano.app.datasource.services.GithubService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GitHubRepositoryImplTest {

    lateinit var repository: GitHubRepository
    lateinit var service: GithubService

    @Before
    fun setUp() {
        service = mockk<GithubService>()
        repository = GitHubRepositoryImpl(service)
        coEvery { service.search(any(), any(), any(), any()) } returns RepoResponse(0, emptyList())
        coEvery { service.pullRequests(any(), any(), any()) } returns emptyList()
    }

    @Test
    fun `When repository get repositories by language Then it should call service search with correct params`() = runBlocking {
        val language = "java"
        val sort = "none"
        val order = "asc"
        val page = 1
        repository.getRepositoriesByLanguage(language, sort, order, page)
        coVerify { service.search("language:$language", sort, order, page) }
        confirmVerified(service)
    }


    @Test
    fun `When repository get pull requests by repository Then it should call service pull requests with correct params`() = runBlocking {
        val owner = "owner"
        val repo = "repo"
        val state = "state"

        repository.getPullRequestsByRepository(owner, repo, state)
        coVerify { service.pullRequests(owner, repo, state) }
        confirmVerified(service)
    }
}