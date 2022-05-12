package com.ilyapiskunov.simplecovidtracker

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.ilyapiskunov.simplecovidtracker.model.SummaryResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET
import java.text.SimpleDateFormat
import java.util.*

interface ApiInterface {
    @GET("/summary")
    fun getSummary(): Call<SummaryResponse>

    companion object Factory {
        fun create() : ApiInterface {

            //Игнорировать неизвестные поля json
            val objectMapper = ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

            val retrofit = Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .baseUrl("https://api.covid19api.com")
                .build()

            return retrofit.create(ApiInterface::class.java)
        }
    }
}