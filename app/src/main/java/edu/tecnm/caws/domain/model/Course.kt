package edu.tecnm.caws.domain.model

import java.time.LocalDate

data class Course(
    val title: String,
    val description: String,
    val initialdate: LocalDate?,
    val finaldate:LocalDate?,
    val place: String
    )
