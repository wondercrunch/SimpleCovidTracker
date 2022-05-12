package com.ilyapiskunov.simplecovidtracker.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class GlobalStat (
                  @JsonProperty("TotalConfirmed")  val totalConfirmed : Long,
                  @JsonProperty("TotalDeaths") val totalDeaths : Long
)


