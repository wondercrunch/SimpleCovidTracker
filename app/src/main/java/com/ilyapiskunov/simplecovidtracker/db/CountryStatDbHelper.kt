package com.ilyapiskunov.simplecovidtracker.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CountryStatDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    private val SQL_CREATE_TABLE = "CREATE TABLE ${CountryStatEntry.TABLE_NAME} (" +
            "${CountryStatEntry._ID} INTEGER PRIMARY KEY," +
            "${CountryStatEntry.NAME_COL} TEXT," +
            "${CountryStatEntry.CODE_COL} TEXT," +
            "${CountryStatEntry.NEW_CONFIRMED_COL} INTEGER," +
            "${CountryStatEntry.TOTAL_CONFIRMED_COL} INTEGER," +
            "${CountryStatEntry.NEW_DEATHS_COL} INTEGER," +
            "${CountryStatEntry.TOTAL_DEATHS_COL} INTEGER," +
            "${CountryStatEntry.NEW_RECOVERED_COL} INTEGER," +
            "${CountryStatEntry.TOTAL_RECOVERED_COL} INTEGER," +
            "${CountryStatEntry.DATE_COL} TEXT" +
            ")"

    private val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS ${CountryStatEntry.TABLE_NAME}"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_TABLE)
        onCreate(db)
    }
}