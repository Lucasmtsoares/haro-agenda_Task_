package com.example.haro_agenda.navegacao.ui.pastas

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PastaModel (
    @PrimaryKey val id: Int,
    val nome: String,
    val dataUltimaModificacao: String,
    val corIcone: String
)