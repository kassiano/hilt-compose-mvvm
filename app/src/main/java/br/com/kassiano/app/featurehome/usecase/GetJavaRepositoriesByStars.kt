package br.com.kassiano.app.featurehome.usecase

import br.com.kassiano.app.datasource.model.Repo
import br.com.kassiano.app.datasource.repository.GitHubRepository
import br.com.kassiano.app.domain.mapper.DomainMapper
import br.com.kassiano.app.domain.model.Repository

class GetJavaRepositoriesByStars(
    private val repository: GitHubRepository,
    private val mapper: DomainMapper<Repo, Repository>
) {
     suspend operator fun invoke(page: Int): List<Repository> {
        val response = repository.getRepositoriesByLanguage(
            language = "Java",
            sort = "stars",
            order = "desc",
            page = page
        )

        return response.items?.map { mapper.toDomain(it) } ?: emptyList()
    }
}