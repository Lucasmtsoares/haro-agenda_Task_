package com.example.haro_agenda.Dao

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.haro_agenda.models.Nota

import com.example.haro_agenda.Database.DatabaseHelper


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
            Log.e("a",cursor.getString(cursor.getColumnIndex("id")))

        }
        return notas
    }

    fun delete(nota: Nota) {
        val db = dbHelper.writableDatabase

        val whereClause = "nome = ? AND descricao = ?"
        val whereArgs = arrayOf(nota.nome, nota.descricao)

        val rowsDeleted = db.delete("nota", whereClause, whereArgs)

        db.close()


    }
    fun update(nota: Nota) {
        val db = dbHelper.writableDatabase

        val contentValues = ContentValues()
        contentValues.put("nome", nota.nome)
        contentValues.put("descricao", nota.descricao)
        Log.e("aaaaa", nota.id.toString())

        val whereClause = "id=" + nota.id
        val whereArgs = arrayOf(nota.id)


        val rowsUpdated = db.update("nota", contentValues, whereClause, null)

        db.close()


    }
}

