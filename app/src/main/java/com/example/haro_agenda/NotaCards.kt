package com.example.haro_agenda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView
import android.widget.SearchView
import com.example.haro_agenda.Dao.NotaDao
import com.example.haro_agenda.databinding.ListaBinding

class NotaCards : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lista)
        supportActionBar?.hide()

        val navbutton = findViewById<Button>(R.id.notas)

        val dbHelper = NotaDao(this)
        var notas = dbHelper.getAll()


        var listView = findViewById<ListView>(R.id.lista)

        var adapter = Adapter(this, notas)

        listView.adapter = adapter

        val searchView = findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrBlank()) {
                    val dbHelper = NotaDao(this@NotaCards)
                    notas = dbHelper.search(query)
                    adapter = Adapter(this@NotaCards, notas)

                    listView.adapter = adapter

                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrBlank()) {
                    val dbHelper = NotaDao(this@NotaCards)
                    notas = dbHelper.getAll()
                    adapter = Adapter(this@NotaCards, notas)

                    listView.adapter = adapter
                }

                return true
            }
        })

        navbutton.setOnClickListener{
            val intent = Intent(this, NotaForm::class.java)
            startActivity(intent)
        }
    }
}