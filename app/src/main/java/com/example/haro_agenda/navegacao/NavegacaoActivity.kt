package com.example.haro_agenda.navegacao

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.haro_agenda.R
import com.example.haro_agenda.databinding.ActivityNavegacaoBinding
import com.example.haro_agenda.loginActivitys.LoginActivity
import com.example.haro_agenda.preferencias.SharedPrefs

class NavegacaoActivity : AppCompatActivity() {

private lateinit var binding: ActivityNavegacaoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
     binding = ActivityNavegacaoBinding.inflate(layoutInflater)
     setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_navegacao)

        //val logoutButton = binding.logout
        //logoutButton.setOnClickListener {
        //    logout()
        //}

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navegacao_notas, R.id.navegacao_tarefas, R.id.navegacao_pastas))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun logout () {

        SharedPrefs(this).logar(false)

        val irParaLogin = Intent(this, LoginActivity::class.java)
        startActivity(irParaLogin)

    }
}