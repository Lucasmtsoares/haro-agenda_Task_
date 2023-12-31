package com.example.haro_agenda

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import com.example.haro_agenda.Dao.NotaDao
import com.example.haro_agenda.databinding.FormNotaBinding
import com.example.haro_agenda.loginActivitys.LoginActivity
import com.example.haro_agenda.models.Nota
import com.example.haro_agenda.navegacao.NavegacaoActivity
import com.example.haro_agenda.navegacao.ui.home.NotasFragment
import com.example.haro_agenda.preferencias.SharedPrefs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NotaForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        val binding = FormNotaBinding.inflate(layoutInflater)

        val button = binding.button
        button.setOnClickListener{

            var nome = binding.editTextNome.text.toString()
            var desc = binding.editTextDescricao.text.toString()

            val scope = MainScope()
            scope.launch {
                withContext(Dispatchers.IO) {
                    val dbHelper = NotaDao(this@NotaForm)
                    var nota = Nota(
                        nome = nome,
                        descricao = desc,
                    )
                    dbHelper.insert(nota)
                    finish()
                }

                val intent = Intent(this@NotaForm, NavegacaoActivity::class.java)
                startActivity(intent)
            }


        }

        val voltar = binding.voltar
        voltar.setOnClickListener{
            val intent = Intent(this, NavegacaoActivity::class.java)
            startActivity(intent)
        }



        setContentView(binding.root)
        supportActionBar?.hide()
    }

    private fun logout () {

        SharedPrefs(this).logar(false)

        val irParaLogin = Intent(this, LoginActivity::class.java)
        startActivity(irParaLogin)

    }

}