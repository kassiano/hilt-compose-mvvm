package br.com.kassiano.app.datasource.services

import br.com.kassiano.app.datasource.model.PullRequestResponse
import br.com.kassiano.app.datasource.model.RepoResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

    @GET("search/repositories")
    suspend fun search(
        @Query("q") query: String,
        @Query("sort") sort: String,
        @Query("order") order: String,
        @Query("page") page: Int
    ): RepoResponse

    @GET("repos/{owner}/{repo}/pulls")
    suspend fun pullRequests(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Query("state") state: String = "open"
    ): List<PullRequestResponse>

}