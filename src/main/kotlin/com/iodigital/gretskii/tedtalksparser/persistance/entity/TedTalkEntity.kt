package com.iodigital.gretskii.tedtalksparser.persistance.entity

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import jakarta.validation.Valid
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.PositiveOrZero
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.validation.annotation.Validated
import java.time.YearMonth

@Entity
@Validated
data class TedTalkEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    var title: String,
    var author: String,
    @JsonFormat(locale = "en", pattern = "MMMM yyyy")
    var date: YearMonth,
    var link: String,
    @field:PositiveOrZero
    var likes: Long,
    @field:PositiveOrZero
    var views: Long,
    var influenceScore: Double = 0.0
) {
    @PreUpdate
    @PrePersist
    private fun calculateInfluence() {
        influenceScore = likes * 0.5 + views * 0.1
    }
}

