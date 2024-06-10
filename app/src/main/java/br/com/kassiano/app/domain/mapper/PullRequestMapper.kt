package br.com.kassiano.app.domain.mapper

import br.com.kassiano.app.datasource.model.PullRequestResponse
import br.com.kassiano.app.domain.model.PullRequest

class PullRequestMapper: DomainMapper<PullRequestResponse, PullRequest> {
    override fun toDomain(model: PullRequestResponse): PullRequest {
        return PullRequest(
            id = model.id,
            title = model.title,
            htmlUrl = model.htmlUrl,
            user = model.user.login,
            avatarUrl = model.user.avatarUrl
        )
    }
}