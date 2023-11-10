package com.example.haro_agenda.models

import com.example.haro_agenda.R

data class TasksClass(
    var id: Int? = null,
    var descricao: String,
    var tag: String,
    var edit: Int = R.drawable.baseline_edit_note_24
)