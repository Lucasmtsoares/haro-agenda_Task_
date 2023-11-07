package com.example.haro_agenda.loginActivitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.Toast
import com.example.haro_agenda.NotaCards
import com.example.haro_agenda.database.LoginDatabaseHelper
import com.example.haro_agenda.NotaForm
import com.example.haro_agenda.R
import com.example.haro_agenda.TasksActivity
import com.example.haro_agenda.databinding.LoginlayoutBinding
import com.example.haro_agenda.navegacao.NavegacaoActivity
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

                    val irParaNotas = Intent(this, NavegacaoActivity::class.java)
                    startActivity(irParaNotas)
                } else {
                    mensagem("Usuario não cadastrado")
                }

                setContentView(layout.root)
            }

            catch (e: Exception) {
                if (e.message == "EmailInvalidoException") {mensagem("Insira um email valido")  }
                if (e.message == "SenhaInvalidaException") {mensagem("Insira uma senha valida")  }
                else {
                    mensagem("Erro Inesperado")
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

    private fun mensagem(mensagem: String) {
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show()
    }

}