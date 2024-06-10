package br.com.kassiano.app.domain.mapper

import br.com.kassiano.app.datasource.model.PullRequestResponse
import br.com.kassiano.app.datasource.model.UserResponse
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class PullRequestMapperTest {

    lateinit var mapper: PullRequestMapper
    @Before
    fun setUp() {
        mapper = PullRequestMapper()
    }

    @Test
    fun `When mapper to domain Then it should map with correct values`() {
        val response = PullRequestResponse(
            id = 0,
            title = "title",
            htmlUrl = "htmlUrl",
            user = UserResponse(login = "login", avatarUrl = "avatarUrl"),
            body = "body"
        )
        val result = mapper.toDomain(response)
        assertEquals(result.id, response.id)
        assertEquals(result.htmlUrl, response.htmlUrl)
        assertEquals(result.user, response.user.login)
        assertEquals(result.avatarUrl, response.user.avatarUrl)
        assertEquals(result.title, response.title)
    }
}