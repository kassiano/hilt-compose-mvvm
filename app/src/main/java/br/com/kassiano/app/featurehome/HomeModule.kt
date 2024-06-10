package br.com.kassiano.app.featurehome

import br.com.kassiano.app.datasource.model.Repo
import br.com.kassiano.app.datasource.repository.GitHubRepository
import br.com.kassiano.app.domain.mapper.DomainMapper
import br.com.kassiano.app.domain.mapper.RepositoryMapper
import br.com.kassiano.app.domain.model.Repository
import br.com.kassiano.app.featurehome.usecase.GetJavaRepositoriesByStars
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    fun provideDomainMapper(): DomainMapper<Repo, Repository> {
        return RepositoryMapper()
    }

    @Provides
    fun provideGetJavaRepositoriesByStars(
        repository: GitHubRepository,
        mapper: DomainMapper<Repo, Repository>): GetJavaRepositoriesByStars {
        return GetJavaRepositoriesByStars(repository, mapper)
    }

    @Provides
    fun providesCoroutineDispatcher() = Dispatchers.IO
}


