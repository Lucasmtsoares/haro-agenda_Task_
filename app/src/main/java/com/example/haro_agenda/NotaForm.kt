package com.example.haro_agenda

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.haro_agenda.Database.NotaDao
import com.example.haro_agenda.databinding.FormNotaBinding
import com.example.haro_agenda.Nota

class NotaForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = FormNotaBinding.inflate(layoutInflater)
        val navbutton = binding.notas
        navbutton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

        val button = binding.button
        button.setOnClickListener{

            var nome = binding.editTextNome.text.toString()
            var desc = binding.editTextDescricao.text.toString()

            val dbHelper = NotaDao(this)
            var nota = Nota(nome,
                desc,
            )
            dbHelper.insert(nota)

        }



        setContentView(binding.root)
    }
}