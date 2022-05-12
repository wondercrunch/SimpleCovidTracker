package com.ilyapiskunov.simplecovidtracker.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.core.database.sqlite.transaction
import com.ilyapiskunov.simplecovidtracker.DATE_FORMAT
import com.ilyapiskunov.simplecovidtracker.model.CountryStat

class CountryStatDAO(context: Context) {

    private val dbHelper = CountryStatDbHelper(context)

    /**
     * Сохранить всю статистику в бд, предварительно очистив таблицу
     */
    fun store(countries: List<CountryStat>) {
        dbHelper.writableDatabase.use {

            it.transaction {
                it.delete(CountryStatEntry.TABLE_NAME, null, null)

                for (country in countries) {
                    val values = ContentValues()

                    values.put(CountryStatEntry.NAME_COL, country.name)
                    values.put(CountryStatEntry.CODE_COL, country.code)
                    values.put(CountryStatEntry.NEW_CONFIRMED_COL, country.newConfirmed)
                    values.put(CountryStatEntry.TOTAL_CONFIRMED_COL, country.totalConfirmed)
                    values.put(CountryStatEntry.NEW_DEATHS_COL, country.newDeaths)
                    values.put(CountryStatEntry.TOTAL_DEATHS_COL, country.totalDeaths)
                    values.put(CountryStatEntry.NEW_RECOVERED_COL, country.newRecovered)
                    values.put(CountryStatEntry.TOTAL_RECOVERED_COL, country.totalRecovered)
                    values.put(CountryStatEntry.DATE_COL, DATE_FORMAT.format(country.date))

                    insert(CountryStatEntry.TABLE_NAME, null, values)
                }
            }
        }
    }

    /**
     * Получить заранее отсортированную по общему количеству кейсов статистику из бд
     */
    fun get() : List<CountryStat> {
        return dbHelper.readableDatabase.use {
            val columns = arrayOf(CountryStatEntry._ID,
                CountryStatEntry.NAME_COL,
                CountryStatEntry.CODE_COL,
                CountryStatEntry.NEW_CONFIRMED_COL,
                CountryStatEntry.TOTAL_CONFIRMED_COL,
                CountryStatEntry.NEW_DEATHS_COL,
                CountryStatEntry.TOTAL_DEATHS_COL,
                CountryStatEntry.NEW_RECOVERED_COL,
                CountryStatEntry.TOTAL_RECOVERED_COL,
                CountryStatEntry.DATE_COL
            )
            it.doQuery(CountryStatEntry.TABLE_NAME, columns, orderBy = "${CountryStatEntry.TOTAL_CONFIRMED_COL} DESC").use {
                    cursor ->
                val countries = mutableListOf<CountryStat>()
                while (cursor.moveToNext()) {
                    val name = cursor.getString(CountryStatEntry.NAME_COL)
                    val code = cursor.getString(CountryStatEntry.CODE_COL)
                    val newConfirmed = cursor.getLong(CountryStatEntry.NEW_CONFIRMED_COL)
                    val totalConfirmed = cursor.getLong(CountryStatEntry.TOTAL_CONFIRMED_COL)
                    val newDeaths = cursor.getLong(CountryStatEntry.NEW_DEATHS_COL)
                    val totalDeaths = cursor.getLong(CountryStatEntry.TOTAL_DEATHS_COL)
                    val newRecovered = cursor.getLong(CountryStatEntry.NEW_RECOVERED_COL)
                    val totalRecovered = cursor.getLong(CountryStatEntry.TOTAL_RECOVERED_COL)
                    val dateString = cursor.getString(CountryStatEntry.DATE_COL)
                    val date = DATE_FORMAT.parse(dateString)
                    countries.add(CountryStat(name, code, newConfirmed, totalConfirmed, newDeaths, totalDeaths, newRecovered, totalRecovered, date))
                }
                countries
            }
        }
    }

}

private fun SQLiteDatabase.doQuery(table : String,
                                   columns : Array<String>,
                                   selection: String? = null,
                                   selectionArgs: Array<String>? = null,
                                   groupBy: String? = null,
                                   having: String? = null,
                                   orderBy: String? = null
) : Cursor {
    return query(table, columns, selection, selectionArgs, groupBy, having, orderBy)
}


private fun Cursor.getString(columnName: String) = getString(getColumnIndexOrThrow(columnName))
private fun Cursor.getLong(columnName: String) = getLong(getColumnIndexOrThrow(columnName))