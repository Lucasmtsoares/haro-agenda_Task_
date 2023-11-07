package com.example.haro_agenda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.haro_agenda.Dao.NotaDao

class NotaCards : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lista)


        val dbHelper = NotaDao(this)
        val notas = dbHelper.getAll()




        var listView = findViewById<ListView>(R.id.lista)

        var adapter = Adapter(this, notas)

        listView.adapter = adapter
    }
}