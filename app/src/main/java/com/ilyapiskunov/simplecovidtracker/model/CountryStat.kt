package com.ilyapiskunov.simplecovidtracker.model


import com.fasterxml.jackson.annotation.JsonClassDescription
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import java.util.*


data class CountryStat(@JsonProperty("Country") val name : String,
                   @JsonProperty("CountryCode") val code : String,
                   @JsonProperty("NewConfirmed") val newConfirmed : Long,
                   @JsonProperty("TotalConfirmed")  val totalConfirmed : Long,
                   @JsonProperty("NewDeaths") val newDeaths: Long,
                   @JsonProperty("TotalDeaths") val totalDeaths : Long,
                   @JsonProperty("NewRecovered") val newRecovered : Long, //Почему-то всегда приходит 0
                   @JsonProperty("TotalRecovered") val totalRecovered: Long,//Аналогично
                   @JsonProperty("Date")
                   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                   val date: Date
)
