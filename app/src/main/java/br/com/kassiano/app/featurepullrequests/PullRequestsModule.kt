package br.com.kassiano.app.featurepullrequests

import br.com.kassiano.app.datasource.model.PullRequestResponse
import br.com.kassiano.app.datasource.repository.GitHubRepository
import br.com.kassiano.app.domain.mapper.DomainMapper
import br.com.kassiano.app.domain.mapper.PullRequestMapper
import br.com.kassiano.app.domain.model.PullRequest
import br.com.kassiano.app.featurepullrequests.usecase.GetPullRequestsByRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PullRequestsModule {

    @Provides
    fun providePullRequestMapper(): DomainMapper<PullRequestResponse, PullRequest> {
        return PullRequestMapper()
    }

    @Provides
    fun provideGetPullRequestsByRepository(
        repository: GitHubRepository,
        mapper: DomainMapper<PullRequestResponse, PullRequest>
    ): GetPullRequestsByRepository {
        return GetPullRequestsByRepository(repository, mapper)
    }
}
