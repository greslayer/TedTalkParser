package com.iodigital.gretskii.tedtalksparser.web.controller

import com.iodigital.gretskii.tedtalksparser.persistance.entity.TedTalkEntity
import com.iodigital.gretskii.tedtalksparser.service.tedtalk.TedTalkServiceImpl
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.RestController

@RestController
class TopControllerImpl(private val service: TedTalkServiceImpl) : TopController {
    override fun getTop(pageable: Pageable, year: Int?): Page<TedTalkEntity> {
        return service.getTopByInfluenceScore(pageable, year)
    }
}