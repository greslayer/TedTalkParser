package com.iodigital.gretskii.tedtalksparser.service.importing

import com.iodigital.gretskii.tedtalksparser.web.dto.ImportResult
import org.springframework.web.multipart.MultipartFile

interface CsvImportService {
    fun importCsv(csv: MultipartFile): ImportResult
}