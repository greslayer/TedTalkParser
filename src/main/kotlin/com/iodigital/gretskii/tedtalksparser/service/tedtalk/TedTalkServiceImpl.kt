package com.iodigital.gretskii.tedtalksparser.service.tedtalk

import com.iodigital.gretskii.tedtalksparser.persistance.entity.TedTalkEntity
import com.iodigital.gretskii.tedtalksparser.persistance.repository.TedTalkRepository
import com.iodigital.gretskii.tedtalksparser.service.utils.YearMonthUtils
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class TedTalkServiceImpl(private val repository: TedTalkRepository) :
    TedTalkService {
    private val influenceScore = Sort.by(TedTalkEntity::influenceScore.name)
    override fun getTopByInfluenceScore(pageable: Pageable, year: Int?): Page<TedTalkEntity> {
        if (year == null) {
            return repository.findAll(pageableSortedByInfluenceScoreFromAnotherPageable(pageable))
        }
        val (before, after) = YearMonthUtils.getSurroundingMonthsFromYear(year)
        return repository.findAllByDateBetween(pageable, before, after)
    }

    private fun pageableSortedByInfluenceScoreFromAnotherPageable(pageable: Pageable): PageRequest {
        return PageRequest.of(pageable.pageNumber, pageable.pageSize, influenceScore.descending())
    }
}