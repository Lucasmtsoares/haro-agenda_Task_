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
        contentValues.put("descricao", task.descricao)
        contentValues.put("tag", task.tag)

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
                val id        = cursor.getInt(cursor.getColumnIndex("id"))
                val descricao      = cursor.getString(cursor.getColumnIndex("descricao"))
                val tag = cursor.getString(cursor.getColumnIndex("tag"))

                val tarefa = TasksClass(id=id,descricao=descricao, tag = tag)
                tarefas.add(tarefa)
            }catch (erro_recuperar_dados: Exception){
                print("Erro ao recuperar dados $erro_recuperar_dados")
            }

        }
        return tarefas
    }
    fun update(task: TasksClass) {
        try {
            val db = db_Helper.writableDatabase
            val contentValues = ContentValues()
            contentValues.put("descricao", task.descricao)
            contentValues.put("tag", task.tag)
            val whereClause = "id=" + task.id
            db.update("tarefa", contentValues, whereClause, null)
            db.close()
        }catch (erro: Exception){
            print("Ocorreu um erro inesperado $erro")
        }
    }

    fun delete(task: TasksClass){
        try {
            val db = db_Helper.writableDatabase
            val whereClause = "id=" + task.id
            db.delete("tarefa", whereClause, null)
            db.close()
        }catch (erro: Exception){
            print("Ocorreu um erro inesperado!! $erro")
        }

    }

}



