package br.com.kassiano.app.datasource

import br.com.kassiano.app.datasource.repository.GitHubRepository
import br.com.kassiano.app.datasource.services.GithubService
import br.com.kassiano.app.domain.repository.GitHubRepositoryImpl
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatasourceModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return  Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    @Provides
    @Singleton
    fun provideGithubService(
        retrofit: Retrofit
    ): GithubService {
        return  retrofit.create(GithubService::class.java)
    }

    @Provides
    fun provideGithubRepository(
        service: GithubService
    ): GitHubRepository {
        return GitHubRepositoryImpl(service)
    }
}



