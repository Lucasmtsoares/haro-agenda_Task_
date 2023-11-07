package com.example.haro_agenda.Dao

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import com.example.haro_agenda.models.Nota

import com.example.haro_agenda.database.DatabaseHelper


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
            val id = cursor.getInt(cursor.getColumnIndex("id"))

            val nota = Nota(id = id,nome = nome, descricao = descricao)
            notas.add(nota)

        }
        return notas
    }

    fun delete(nota: Nota) {
        val db = dbHelper.writableDatabase

        val whereClause = "id=" + nota.id

        db.delete("nota", whereClause, null)

        db.close()


    }
    fun update(nota: Nota) {
        val db = dbHelper.writableDatabase

        val contentValues = ContentValues()
        contentValues.put("nome", nota.nome)
        contentValues.put("descricao", nota.descricao)

        val whereClause = "id=" + nota.id

        db.update("nota", contentValues, whereClause, null)

        db.close()

    }
}

