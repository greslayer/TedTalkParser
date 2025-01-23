package com.iodigital.gretskii.tedtalksparser.service.utils

import java.time.YearMonth

object YearMonthUtils {
    fun getSurroundingMonthsFromYear(year: Int): Pair<YearMonth, YearMonth> =
        Pair(YearMonth.of(year, 1), YearMonth.of(year, 12))
}