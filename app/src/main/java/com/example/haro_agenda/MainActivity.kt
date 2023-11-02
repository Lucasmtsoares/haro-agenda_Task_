package com.example.haro_agenda

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import com.example.haro_agenda.Database.NotaDao

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lista)

        var nota1 = Nota("nota1",
            "descrição1",
            )

        var nota2 = Nota("nota2",
            "descrição2",
            )

        val dbHelper = NotaDao(this)
        //dbHelper.insert(nota1)
        val notas = dbHelper.getAll()
        //Log.e("a","${notas.size}")


        var list = arrayListOf(nota1, nota2)
        var listView = findViewById<ListView>(R.id.lista)

        var adapter = Adapter(this, notas)

        listView.adapter = adapter
    }
}