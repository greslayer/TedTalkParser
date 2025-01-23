package com.iodigital.gretskii.tedtalksparser.web.converter

import com.iodigital.gretskii.tedtalksparser.persistance.entity.TedTalkEntity
import org.apache.commons.csv.CSVRecord
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class CsvRecordToTedTalkEntityConverter(private val yearMonthParser: FullMonthStringToYearMonthConverter) :
    Converter<CSVRecord, TedTalkEntity> {

    override fun convert(record: CSVRecord): TedTalkEntity? {
        return TedTalkEntity(
            title = record["title"],
            author = record["author"],
            date = yearMonthParser.convert(record["date"]),
            views = record["views"].toLong(),
            likes = record["likes"].toLong(),
            link = record["link"]
        )
    }
}