package com.example.haro_agenda.Entidades

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    val email: String,
    val nome: String,
    val senha: String
)