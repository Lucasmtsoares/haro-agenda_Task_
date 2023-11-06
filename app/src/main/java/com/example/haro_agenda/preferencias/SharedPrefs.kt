package com.example.haro_agenda.preferencias

import android.content.Context

class SharedPrefs (context: Context){

    private val storage = context.getSharedPreferences("user_session", Context.MODE_PRIVATE)

    fun logar(value : Boolean) { storage.edit().putBoolean("EstaLogado", value).apply() }

    fun estaLogado() : Boolean { return storage.getBoolean("EstaLogado", false) }

}