package com.example.haro_agenda.loginActivitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.haro_agenda.database.LoginDatabaseHelper
import com.example.haro_agenda.Entidades.User
import com.example.haro_agenda.databinding.RegistrolayoutBinding


class RegistroActivity : AppCompatActivity() {

    private lateinit var layout: RegistrolayoutBinding

    private val dao by lazy {
        LoginDatabaseHelper.pegarInstancia(this).userDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        layout = RegistrolayoutBinding.inflate(layoutInflater)
        setContentView(layout.root)

        layout.navegarLogin.setOnClickListener {
            val irParaLogin = Intent(this, LoginActivity::class.java)
            startActivity(irParaLogin)
        }

        layout.botao.setOnClickListener{
            val nome = layout.inputNome.text.toString()
            val email = layout.inputEmail.text.toString()
            val senha = layout.inputSenha.text.toString()

            val usuario = User( email, nome, senha)


            try {
                if (nome == "") {throw Exception("NomeInvalidoException") }
                if (!email.contains("@")) {throw Exception("EmailInvalidoException") }
                if (senha.length < 8) {throw Exception("SenhaInvalidaException") }

                dao.registrar(usuario)
                finish()

                Toast.makeText(this, "Usuario Cadastrado", Toast.LENGTH_LONG).show()

                val irParaLogin = Intent(this, LoginActivity::class.java)
                startActivity(irParaLogin)
            }

            catch (e: Exception) {
                if (e.message == "NomeInvalidoException") { Toast.makeText(this, "Insira um nome valido", Toast.LENGTH_LONG).show() }
                if (e.message == "EmailInvalidoException") { Toast.makeText(this, "Insira um email valido", Toast.LENGTH_LONG).show() }
                if (e.message == "SenhaInvalidaException") { Toast.makeText(this, "Insira uma senha valida", Toast.LENGTH_LONG).show() }
                else {
                    Toast.makeText(this, "Email já em uso. Insira um email diferente", Toast.LENGTH_LONG).show()
                }
            }

        }

        setContentView(layout.root)



    }
}
