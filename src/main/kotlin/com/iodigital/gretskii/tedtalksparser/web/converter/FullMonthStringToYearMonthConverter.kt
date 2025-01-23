package com.iodigital.gretskii.tedtalksparser.web.converter

import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.*

@Component
class FullMonthStringToYearMonthConverter : Converter<String, YearMonth> {
    private val formatter = DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH)
    override fun convert(source: String): YearMonth {
        return formatter.parse(source, YearMonth::from)
    }
}