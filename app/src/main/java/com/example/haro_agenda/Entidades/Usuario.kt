package com.example.haro_agenda.Entidades

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey (autoGenerate = true)
    val id: Int ,
    val email: String,
    val nome: String,
    val senha: String
)