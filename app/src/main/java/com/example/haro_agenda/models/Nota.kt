package com.example.haro_agenda.models
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

import kotlin.random.Random
@Parcelize
data class Nota(
    var id: Int? = null,
    var nome: String,
    var descricao: String
): Parcelable
