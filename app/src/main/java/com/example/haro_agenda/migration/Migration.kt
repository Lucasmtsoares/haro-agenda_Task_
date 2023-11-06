package com.example.register


import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(5, 6) {
    override fun migrate(database: SupportSQLiteDatabase) {

        val sql = "CREATE TABLE IF NOT EXISTS `User` (`email` TEXT NOT NULL, `nome` TEXT NOT NULL, `senha` TEXT NOT NULL, PRIMARY KEY(`email`))"

        database.execSQL(sql)
    }
}