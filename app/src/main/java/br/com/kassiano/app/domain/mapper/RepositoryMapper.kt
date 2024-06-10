package br.com.kassiano.app.domain.mapper

import br.com.kassiano.app.datasource.model.Repo
import br.com.kassiano.app.domain.model.Repository


class RepositoryMapper: DomainMapper<Repo,Repository > {
    override fun toDomain(model: Repo): Repository {
        return Repository(
            id = model.id,
            name = model.name ,
            description = model.description,
            forksCount = model.forksCount,
            stargazersCount = model.stargazersCount,
            owner = model.owner.login
        )
    }
}