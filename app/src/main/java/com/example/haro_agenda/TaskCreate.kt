package com.example.haro_agenda

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.haro_agenda.Dao.TaskDAO
import com.example.haro_agenda.databinding.ActivityTaskCreateBinding
import com.example.haro_agenda.databinding.TaskCardBinding
import com.example.haro_agenda.models.TasksClass

class TaskCreate : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_task_create)

        var binding = ActivityTaskCreateBinding.inflate(layoutInflater)
        var my_tasks = binding.salvar
        var voltar = binding.voltar
        var topico = "Sem nome"
        var topico_trab    = binding.titleTrab
        var topico_evento  = binding.titleEvent
        var topico_pessoal = binding.titlePessoal
        var topico_escola  = binding.titleEscola

        //..

        //..

        topico_trab.setOnClickListener{

            topico = topico_trab.text.toString()
            topico_trab.setBackgroundColor(getColor(R.color.teal_800))

        }
        topico_evento.setOnClickListener{
            topico = topico_evento.text.toString()
            topico_evento.setBackgroundColor(getColor(R.color.purple_800))
        }
        topico_pessoal.setOnClickListener{
            topico = topico_pessoal.text.toString()
            topico_pessoal.setBackgroundColor(getColor(R.color.purple_600))

        }
        topico_escola.setOnClickListener{
            topico = topico_escola.text.toString()
            topico_escola.setBackgroundColor(getColor(R.color.green_600))
        }


        try {
            my_tasks.setOnClickListener{

                var descricao = binding.create.text.toString()
                var tag = topico


                val dbHelper = TaskDAO(this)
                var tarefa = TasksClass(descricao=descricao, tag = tag,)
                dbHelper.salvar(tarefa)
                finish()
                val intent = Intent(this, TasksActivity::class.java)
                startActivity(intent)
            }
            voltar.setOnClickListener{
                val intent = Intent(this, TasksActivity::class.java)
                startActivity(intent)
            }

            setContentView(binding.root)
        }catch (erro: Exception){
            print("Ocorreu um erro inesperado!! $erro")
        }
    }
}


