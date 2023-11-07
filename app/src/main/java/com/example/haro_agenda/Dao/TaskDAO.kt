package com.example.haro_agenda.Dao

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.haro_agenda.database.DatabaseHelper
import com.example.haro_agenda.models.Nota
import com.example.haro_agenda.models.TasksClass

class TaskDAO (context: Context) {
    val db_Helper = DatabaseHelper(context)

    fun salvar(task: TasksClass){
        var db = db_Helper.writableDatabase
        var contentValues = ContentValues()
        contentValues.put("nome_task", task.nome)
        contentValues.put("descricao_task", task.descricao)

        try {
            db.insert("tarefa", null, contentValues)
        }catch (erro_insert: Exception) {
            print("Ocorreu um erro inesperado ao inserir $erro_insert")
        }
        db.close()
    }

    @SuppressLint("Range")
    fun get(): ArrayList<TasksClass>{

        val tarefas: ArrayList<TasksClass> = ArrayList()
        val db = db_Helper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM tarefa", null)

        while (cursor.moveToNext()) {

            try {
                val nome      = cursor.getString(cursor.getColumnIndex("nome_task"))
                val descricao = cursor.getString(cursor.getColumnIndex("descricao_task"))

                val tarefa = TasksClass(nome,descricao)
                tarefas.add(tarefa)
            }catch (erro_recuperar_dados: Exception){
                print("Erro ao recuperar dados $erro_recuperar_dados")
            }

        }
        return tarefas
    }

}



