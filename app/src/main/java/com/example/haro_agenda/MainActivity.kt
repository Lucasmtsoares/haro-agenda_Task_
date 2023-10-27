package com.example.haro_agenda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lista)

        var nota1 = Nota("nota1",
            "descrição1",
            "data1")

        var nota2 = Nota("nota2",
            "descrição2",
            "data2")



        var list = arrayListOf(nota1, nota2)
        var listView = findViewById<ListView>(R.id.lista)

        var adapter = Adapter(this, list)

        listView.adapter = adapter
    }
}