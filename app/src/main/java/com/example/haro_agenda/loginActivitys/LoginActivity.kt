package com.example.haro_agenda.loginActivitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import com.example.haro_agenda.database.LoginDatabaseHelper
import com.example.haro_agenda.NotaForm
import com.example.haro_agenda.databinding.LoginlayoutBinding
import com.example.haro_agenda.preferencias.SharedPrefs

class LoginActivity : AppCompatActivity() {

    private lateinit var layout: LoginlayoutBinding

    private val dao by lazy {
        LoginDatabaseHelper.pegarInstancia(this).userDao()
    }

    override fun onStart() {
        super.onStart()

        if (SharedPrefs(this).estaLogado()) {
            val irParaNotas = Intent(this, NotaForm::class.java)
            startActivity(irParaNotas)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        layout = LoginlayoutBinding.inflate(layoutInflater)
        setContentView(layout.root)

        ganchoBotaoRegistro()
        ganchoBotaoLogin()

    }

    private fun ganchoBotaoLogin() {
        layout.botao.setOnClickListener{
            val email = layout.inputEmail.text.toString()
            val senha = layout.inputSenha.text.toString()

            try {
                if (!email.contains("@")) {throw Exception("EmailInvalidoException") }
                if (senha.length < 8) {throw Exception("SenhaInvalidaException") }

                if (dao.verificarLogin(email, senha)) {

                    SharedPrefs(this).logar(true)

                    val irParaNotas = Intent(this, NotaForm::class.java)
                    startActivity(irParaNotas)
                }

                setContentView(layout.root)
            }

            catch (e: Exception) {
                if (e.message == "NomeInvalidoException") { Toast.makeText(this, "Insira um nome valido", Toast.LENGTH_LONG).show() }
                if (e.message == "EmailInvalidoException") { Toast.makeText(this, "Insira um email valido", Toast.LENGTH_LONG).show() }
                if (e.message == "SenhaInvalidaException") { Toast.makeText(this, "Insira uma senha valida", Toast.LENGTH_LONG).show() }
                else {
                    Toast.makeText(this, "Erro Inesperado", Toast.LENGTH_LONG).show()
                }
            }

        }
    }

    private fun ganchoBotaoRegistro() {
        layout.navegarCadastro.setOnClickListener {


            val irParaCadastro = Intent(this, RegistroActivity::class.java)
            startActivity(irParaCadastro)
        }
    }

}