package com.iodigital.gretskii.tedtalksparser.persistance.repository

import com.iodigital.gretskii.tedtalksparser.persistance.entity.TedTalkEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import java.time.YearMonth


@RepositoryRestResource(path = "talks")
interface TedTalkRepository : PagingAndSortingRepository<TedTalkEntity, Long>,
    CrudRepository<TedTalkEntity, Long> {
    fun findAllByDateBetween(pageable: Pageable, before: YearMonth?, after: YearMonth?): Page<TedTalkEntity>

}