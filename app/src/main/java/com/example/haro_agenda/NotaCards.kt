package com.example.haro_agenda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import com.example.haro_agenda.Dao.NotaDao
import com.example.haro_agenda.databinding.ListaBinding

class NotaCards : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lista)


        val binding = ListaBinding.inflate(layoutInflater)
        val navbutton = findViewById<Button>(R.id.notas)


        val dbHelper = NotaDao(this)
        val notas = dbHelper.getAll()




        var listView = findViewById<ListView>(R.id.lista)

        var adapter = Adapter(this, notas)

        listView.adapter = adapter

        navbutton.setOnClickListener{
            val intent = Intent(this, NotaForm::class.java)
            startActivity(intent)

        }
    }
}