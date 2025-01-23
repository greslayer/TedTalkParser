package com.iodigital.gretskii.tedtalksparser.service.importing

import com.iodigital.gretskii.tedtalksparser.persistance.entity.TedTalkEntity
import com.iodigital.gretskii.tedtalksparser.persistance.repository.TedTalkRepository
import com.iodigital.gretskii.tedtalksparser.web.converter.CsvRecordToTedTalkEntityConverter
import com.iodigital.gretskii.tedtalksparser.web.dto.ImportResult
import mu.KLogging
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.apache.commons.csv.CSVRecord
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.lang.Exception
import java.time.YearMonth
import java.util.concurrent.atomic.AtomicInteger

@Service
class CsvImportServiceImpl(
    private val repository: TedTalkRepository,
    private val csvToEntityConverter: CsvRecordToTedTalkEntityConverter
) : CsvImportService {
    companion object : KLogging()

    private val csvFormat = CSVFormat.Builder.create(CSVFormat.DEFAULT).setHeader().get()
    private val firstMonthOfTedTalks = YearMonth.of(2006, 6)

    override fun importCsv(csv: MultipartFile): ImportResult {
        val csvParser = CSVParser.builder()
            .setReader(csv.inputStream.bufferedReader())
            .setFormat(csvFormat)
            .get()
        val rowsInCsv = AtomicInteger()

        val importedSuccessfully = csvParser.asSequence()
            .onEach { rowsInCsv.incrementAndGet() }
            .map(this::parseTedTalk)
            .filterNotNull()
            .filter(this::valid)
            .map(this::saveTalkWithHandledValidationException)
            .filterNotNull()
            .count()

        return ImportResult(importedSuccessfully = importedSuccessfully, rowsInCsv = rowsInCsv.get())
    }

    private fun valid(tedTalkEntity: TedTalkEntity): Boolean {
        val likesBiggerThenViews = tedTalkEntity.likes <= tedTalkEntity.views
        val happenedBeforeTheFirstTedTalk = tedTalkEntity.date.isBefore(firstMonthOfTedTalks)
        val valid = likesBiggerThenViews || happenedBeforeTheFirstTedTalk
        if (!valid) {
            logger.info { "Found and removed cheated entity: $tedTalkEntity" }
        }
        return valid
    }

    private fun saveTalkWithHandledValidationException(tedTalkEntity: TedTalkEntity): TedTalkEntity? {
        try {
            return repository.save(tedTalkEntity)
        } catch (exception: Exception) {
            logger.error { "Exception: $exception occurred while saving and validating $tedTalkEntity" }
        }
        return null
    }

    private fun parseTedTalk(record: CSVRecord): TedTalkEntity? {
        try {
            return csvToEntityConverter.convert(record)
        } catch (exception: Exception) {
            logger.error { "Exception: $exception occurred while parsing $record" }
        }
        return null
    }


}