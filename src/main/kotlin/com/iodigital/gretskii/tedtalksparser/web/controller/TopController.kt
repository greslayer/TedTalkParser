package com.iodigital.gretskii.tedtalksparser.web.controller

import com.iodigital.gretskii.tedtalksparser.persistance.entity.TedTalkEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@RequestMapping("/top")
interface TopController {
    @GetMapping
    fun getTop(pageable: Pageable, @RequestParam(required = false) year: Int?): Page<TedTalkEntity>
}