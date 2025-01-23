package com.iodigital.gretskii.tedtalksparser.service.tedtalk

import com.iodigital.gretskii.tedtalksparser.persistance.entity.TedTalkEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface TedTalkService {
    fun getTopByInfluenceScore(pageable: Pageable, year: Int?): Page<TedTalkEntity>

}