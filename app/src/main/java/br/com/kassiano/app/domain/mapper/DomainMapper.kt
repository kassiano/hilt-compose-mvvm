package br.com.kassiano.app.domain.mapper

interface DomainMapper<T, D> {
    fun toDomain(model: T): D
}