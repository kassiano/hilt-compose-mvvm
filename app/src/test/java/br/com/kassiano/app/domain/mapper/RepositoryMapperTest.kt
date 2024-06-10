package br.com.kassiano.app.domain.mapper

import br.com.kassiano.app.datasource.model.Owner
import br.com.kassiano.app.datasource.model.Repo
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class RepositoryMapperTest {

    lateinit var mapper: RepositoryMapper

    @Before
    fun setUp() {
        mapper = RepositoryMapper()
    }

    @Test
    fun `When mapper to domain Then it should map with correct values`() {
        val response = Repo(
            id = 0,
            name = "name",
            description = "description",
            forksCount = 0,
            stargazersCount = 0,
            owner = Owner("login")
        )
        val result = mapper.toDomain(response)
        assertEquals(result.id, response.id)
        assertEquals(result.name, response.name)
        assertEquals(result.stargazersCount, response.stargazersCount)
        assertEquals(result.forksCount, response.forksCount)
        assertEquals(result.description, response.description)
    }
}