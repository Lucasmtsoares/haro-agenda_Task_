package com.example.haro_agenda.Database


import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.haro_agenda.Nota

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

    fun insert(nota: Nota) {

        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put("nome", nota.nome)
        contentValues.put("descricao", nota.descricao)

        db.insert("nota", null, contentValues)

        db.close()

    }

    @SuppressLint("Range")
    fun getAll(): ArrayList<Nota> {
        val notas: ArrayList<Nota> = ArrayList()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM nota", null)

        while (cursor.moveToNext()) {

            val nome = cursor.getString(cursor.getColumnIndex("nome"))
            val descricao = cursor.getString(cursor.getColumnIndex("descricao"))

            val nota = Nota(nome,descricao)
            notas.add(nota)

        }
        return notas
    }

}
