package com.example.haro_agenda

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import com.example.haro_agenda.Dao.NotaDao
import com.example.haro_agenda.databinding.FormNotaBinding
import com.example.haro_agenda.loginActivitys.LoginActivity
import com.example.haro_agenda.models.Nota
import com.example.haro_agenda.preferencias.SharedPrefs

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
            var nota = Nota(nome = nome,
                descricao = desc,
            )
            dbHelper.insert(nota)

        }

        val logoutButton = binding.logout
        logoutButton.setOnClickListener {
            logout()
        }

        setContentView(binding.root)
    }

    private fun logout () {

        SharedPrefs(this).logar(false)

        val irParaLogin = Intent(this, LoginActivity::class.java)
        startActivity(irParaLogin)

    }

}