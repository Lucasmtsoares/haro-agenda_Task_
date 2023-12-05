package com.example.haro_agenda

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import com.example.haro_agenda.Dao.NotaDao
import com.example.haro_agenda.databinding.FormNotaBinding
import com.example.haro_agenda.models.Nota
import com.example.haro_agenda.navegacao.NavegacaoActivity

class NotaEdit : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        val nota = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("nota", Nota::class.java)
        } else {
            intent.getParcelableExtra("nota")
        }

        val binding = FormNotaBinding.inflate(layoutInflater)
        binding.editTextNome.setText(nota?.nome)
        binding.editTextDescricao.setText(nota?.descricao)

        val button = binding.button
        button.setOnClickListener{
            var id = nota?.id ?: 0
            var nome = binding.editTextNome.text.toString()
            var desc = binding.editTextDescricao.text.toString()


            val dbHelper = NotaDao(this)
            var nota = Nota(id = id,nome = nome,
                descricao = desc,
            )
            dbHelper.update(nota)

            val intent = Intent(this, NavegacaoActivity::class.java)
            startActivity(intent)

        }



        setContentView(binding.root)
        supportActionBar?.hide()
    }
}