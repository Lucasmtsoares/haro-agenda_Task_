package com.example.haro_agenda.models

import com.example.haro_agenda.R

data class TasksClass(
    var nome: String,
    var descricao: String,
    var edit: Int = R.drawable.baseline_edit_note_24
)