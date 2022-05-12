package com.ilyapiskunov.simplecovidtracker.model

import com.fasterxml.jackson.annotation.JsonProperty

data class SummaryResponse(@JsonProperty("Global") val global: GlobalStat,
                           @JsonProperty("Countries") val countries: List<CountryStat>
)