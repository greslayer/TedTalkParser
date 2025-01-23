package com.iodigital.gretskii.tedtalksparser.web.controller

import com.iodigital.gretskii.tedtalksparser.service.importing.CsvImportService
import com.iodigital.gretskii.tedtalksparser.web.dto.ImportResult
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
class CsvImportControllerImpl(private val service: CsvImportService) : CsvImportController {
    override fun importCsv(csv: MultipartFile): ImportResult {
        return service.importCsv(csv)
    }
}