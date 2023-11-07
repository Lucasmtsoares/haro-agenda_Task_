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
            irParaLogin()
        }

        layout.botao.setOnClickListener{
            val nome = layout.inputNome.text.toString()
            val email = layout.inputEmail.text.toString()
            val senha = layout.inputSenha.text.toString()
            val confirmarSenha = layout.inputConfirmarSenha.text.toString()

            val usuario = User( email, nome, senha)


            try {
                if (nome == "") {throw Exception("NomeInvalidoException") }
                if (!email.contains("@")) {throw Exception("EmailInvalidoException") }
                if (senha.length < 8) {throw Exception("SenhaInvalidaException") }
                if (senha != confirmarSenha) {throw Exception("SenhaNaoConfereException")}

                dao.registrar(usuario)
                finish()

                mensagem("Usuario Cadastrado")

                irParaLogin()
            }

            catch (e: Exception) {
                if (e.message == "NomeInvalidoException") {mensagem("Insira um nome valido") }
                if (e.message == "EmailInvalidoException") {mensagem("Insira um email valido")}
                if (e.message == "SenhaInvalidaException") {mensagem("Insira uma senha valida") }
                if (e.message == "SenhaNaoConfereException") {mensagem("As senhas não coincidem") }

                else {
                    mensagem("Email já em uso. Insira um email diferente")
                }
            }

        }

        setContentView(layout.root)



    }


    private fun irParaLogin() {
        val enderecoLogin = Intent(this, LoginActivity::class.java)
        startActivity(enderecoLogin)
    }

    private fun mensagem(mensagem: String) {
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show()
    }
}
