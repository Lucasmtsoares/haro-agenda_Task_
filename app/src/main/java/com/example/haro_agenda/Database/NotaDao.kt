package com.example.haro_agenda.Database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.haro_agenda.Nota

import android.database.Cursor
import android.database.SQLException


class NotaDao (context: Context) {
    val dbHelper= DatabaseHelper(context)

    fun insert(nota: Nota) {
        val db = dbHelper.writableDatabase

        val contentValues = ContentValues()
        contentValues.put("nome", nota.nome)
        contentValues.put("descricao", nota.descricao)

        db.insert("nota", null, contentValues)

        db.close()

    }

    @SuppressLint("Range")
    fun getAll(): ArrayList<Nota> {
        val notas: ArrayList<Nota> = ArrayList()
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM nota", null)

        while (cursor.moveToNext()) {

            val nome = cursor.getString(cursor.getColumnIndex("nome"))
            val descricao = cursor.getString(cursor.getColumnIndex("descricao"))

            val nota = Nota(nome,descricao)
            notas.add(nota)
            //Log.e("a",nome)

        }
        return notas
    }
}