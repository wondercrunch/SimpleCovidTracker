package com.ilyapiskunov.simplecovidtracker.db

import android.provider.BaseColumns

val DATABASE_NAME = "countries.db"
val DATABASE_VERSION = 10

object CountryStatEntry : BaseColumns {
    val TABLE_NAME = "countries"
    val _ID = "id"
    val NAME_COL = "name"
    val CODE_COL = "code"
    val NEW_CONFIRMED_COL = "new_confirmed"
    val TOTAL_CONFIRMED_COL = "total_confirmed"
    val NEW_DEATHS_COL = "new_deaths"
    val TOTAL_DEATHS_COL = "total_deaths"
    val NEW_RECOVERED_COL = "new_recovered"
    val TOTAL_RECOVERED_COL = "total_recovered"
    val DATE_COL = "date"
}