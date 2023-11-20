package com.example.haro_agenda.navegacao.ui.pastas

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PastaModel(
    @PrimaryKey val id: Int? = null,
    var idEncadeado: Int = -1,
    val nome: String,
    val dataUltimaModificacao: String,
    val corIcone: String
)