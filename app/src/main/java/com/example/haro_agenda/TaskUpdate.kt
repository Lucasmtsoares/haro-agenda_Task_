package com.example.haro_agenda

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.haro_agenda.Dao.TaskDAO
import com.example.haro_agenda.databinding.ActivityTaskCreateBinding
import com.example.haro_agenda.databinding.ActivityTaskUpdateBinding
import com.example.haro_agenda.models.TasksClass

class TaskUpdate : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding = ActivityTaskUpdateBinding.inflate(layoutInflater)
        var my_tasks = binding.update
        var voltar = binding.voltar
        val taskTag = intent.getStringExtra("tag")
        var topico = taskTag.toString()

        var topico_trab    = binding.titleTrab
        var topico_evento  = binding.titleEvent
        var topico_pessoal = binding.titlePessoal
        var topico_escola  = binding.titleEscola

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
            val taskId = intent.getIntExtra("id", 0)
            val taskDesc = intent.getStringExtra("descricao")
                //val taskTag = intent.getStringExtra("tag")

            var text_create = binding.createUpdate
            var tag_text = binding.titleTag

            text_create.setText(taskDesc)
            //tag_text.   setText(taskTag)

            my_tasks.setOnClickListener{

                var descricao = binding.createUpdate.text.toString()
                var tag = topico

                //...

                val dbHelper = TaskDAO(this)
                var tarefa = TasksClass(id=taskId, descricao=descricao, tag = tag,)
                dbHelper.update(tarefa)
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


