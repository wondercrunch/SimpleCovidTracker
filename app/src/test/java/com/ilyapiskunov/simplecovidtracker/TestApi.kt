package com.ilyapiskunov.simplecovidtracker


import org.junit.Test
import org.junit.Assert.*


class TestApi {

    @Test
    fun testResponse() {
        val apiCall = ApiInterface.create()
        val response = apiCall.getSummary().execute()
        assertTrue(response.isSuccessful)
        assertNotNull(response.body())
    }
}