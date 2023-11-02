package com.example.haro_agenda.Database


import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        const val DATABASE_NAME = "haro-agenda"
        const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL("CREATE TABLE nota (id INTEGER PRIMARY KEY, nome TEXT, descricao Text)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        db.execSQL("DROP TABLE IF EXISTS nota")
        onCreate(db)
    }

}
