package com.example.haro_agenda.migration


import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRAR = object : Migration(6, 7) {
    override fun migrate(database: SupportSQLiteDatabase) {

        val sql1 = "DROP TABLE IF EXISTS `USER`"
        val sql = "CREATE TABLE IF NOT EXISTS `User` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,`nome` TEXT NOT NULL, `email` TEXT NOT NULL, `senha` TEXT NOT NULL)"

        database.execSQL(sql1)
        database.execSQL(sql)
    }
}