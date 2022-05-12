package com.ilyapiskunov.simplecovidtracker


import com.ilyapiskunov.simplecovidtracker.db.CountryStatDAO
import com.ilyapiskunov.simplecovidtracker.model.CountryStat
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*
import org.junit.Assert.*
import org.junit.Before
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import java.text.SimpleDateFormat

@RunWith(RobolectricTestRunner::class)
class TestDB {

    private lateinit var countryStatDAO : CountryStatDAO

    @Before
    fun init() {
        countryStatDAO = CountryStatDAO(RuntimeEnvironment.getApplication())
    }

    @Test
    fun testStoreAndGet() {
        val format = DATE_FORMAT
        val dateString = format.format(Date())
        val date = format.parse(dateString)!!
        val countries = listOf(CountryStat("Russia", "RU", 1000, 10000, 111, 1111, 222, 2222, date),
           CountryStat("USA", "US", 2000, 20000, 333, 3333, 444, 4444, date)

        )
        countryStatDAO.store(countries)
        val storedCountries = countryStatDAO.get()
        println(storedCountries)
        assertTrue(storedCountries.containsAll(countries))
    }
}