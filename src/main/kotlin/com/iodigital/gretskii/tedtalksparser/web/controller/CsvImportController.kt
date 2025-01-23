package com.iodigital.gretskii.tedtalksparser.web.controller

import com.iodigital.gretskii.tedtalksparser.web.dto.ImportResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.multipart.MultipartFile

@RequestMapping("/import")
interface CsvImportController {
    @PostMapping("/file")
    fun importCsv(@RequestBody csv: MultipartFile): ImportResult
}