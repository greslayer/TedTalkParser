package com.iodigital.gretskii.tedtalksparser.web.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ImportResult(
    val importedSuccessfully: Int,
    val rowsInCsv: Int
) {
    @JsonProperty("importedSuccessfullyInPercentage")
    fun countSuccessRate(): String {
        if (rowsInCsv == 0) {
            return "100%"
        }
        return "${(importedSuccessfully.toDouble() / rowsInCsv * 100)}%"
    }
}
