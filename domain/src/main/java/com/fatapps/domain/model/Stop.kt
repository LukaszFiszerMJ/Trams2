package com.fatapps.domain.model

data class Stop(
    val direction: String,
    val lat: Double,
    val line: String,
    val lon: Double,
    val name: String,
    val stopId: String
)