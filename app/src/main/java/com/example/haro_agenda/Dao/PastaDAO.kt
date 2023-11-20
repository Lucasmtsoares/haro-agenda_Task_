package com.example.haro_agenda.Dao

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import com.example.haro_agenda.database.DatabaseHelper
import com.example.haro_agenda.navegacao.ui.pastas.PastaModel

class PastaDAO (context: Context) {
    val dbHelper = DatabaseHelper(context)
    val tabelaNome = "pastas"

    fun salvarPasta(pasta: PastaModel){
        var db = dbHelper.writableDatabase
        var contentValues = ContentValues()

        contentValues.put("nome", pasta.nome)
        contentValues.put("id_encadeado", pasta.idEncadeado)
        contentValues.put("data_ultima_modificacao", pasta.dataUltimaModificacao)
        contentValues.put("cor_icone", pasta.corIcone)

        try {
            db.insert(tabelaNome, null, contentValues)
        }catch (erroInserir: Exception) {
            print("Ocorreu um erro inesperado ao inserir $erroInserir")
        }
        db.close()
    }

    @SuppressLint("Range")
    fun listarPastas(idParaFiltrar: Int = -1): ArrayList<PastaModel>{

        val pastas: ArrayList<PastaModel> = ArrayList()
        val db = dbHelper.readableDatabase

        val cursor = if (idParaFiltrar > -1)
            db.rawQuery("SELECT * FROM $tabelaNome WHERE id_encadeado = ?", arrayOf(idParaFiltrar.toString()))
        else
            db.rawQuery("SELECT * FROM $tabelaNome", null)

        while (cursor.moveToNext()) {

            try {
                val id                    = cursor.getInt(cursor.getColumnIndex("id"))
                val nome                  = cursor.getString(cursor.getColumnIndex("nome"))
                val idEncadeado           = cursor.getInt(cursor.getColumnIndex("id_encadeado"))
                val dataUltimaModificacao = cursor.getString(cursor.getColumnIndex("data_ultima_modificacao"))
                val corIcone              = cursor.getString(cursor.getColumnIndex("cor_icone"))

                val pasta = PastaModel(id = id,
                                        nome = nome,
                                        idEncadeado = idEncadeado,
                                        dataUltimaModificacao = dataUltimaModificacao,
                                        corIcone = corIcone)
                pastas.add(pasta)

            } catch (erroRecuperarDados: Exception) {
                print("Erro ao recuperar dados $erroRecuperarDados")
            }

        }
        return pastas
    }
    fun atualizarPasta(pasta: PastaModel) {
        try {
            val db = dbHelper.writableDatabase

            val contentValues = ContentValues()
            contentValues.put("nome", pasta.nome)
            contentValues.put("id_encadeado", pasta.dataUltimaModificacao)
            contentValues.put("data_ultima_modificacao", pasta.dataUltimaModificacao)
            contentValues.put("cor_icone", pasta.corIcone)

            val whereClause = "id=" + pasta.id
            db.update(tabelaNome, contentValues, whereClause, null)
            db.close()

        } catch (erro: Exception) {
            print("Ocorreu um erro inesperado $erro")
        }
    }

    fun deletarPasta(pasta: PastaModel){
        try {
            val db = dbHelper.writableDatabase
            val whereClause = "id=" + pasta.id
            db.delete(tabelaNome, whereClause, null)
            db.close()
        } catch (erro: Exception) {
            print("Ocorreu um erro inesperado!! $erro")
        }

    }

}
