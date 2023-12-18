package com.example.haro_agenda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import com.example.haro_agenda.Dao.NotaDao
import com.example.haro_agenda.Dao.TaskDAO
import com.example.haro_agenda.models.TasksClass
import kotlinx.coroutines.NonCancellable.start

class TasksActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)
        supportActionBar?.hide()

        val dbHelper = TaskDAO(this)
        //dbHelper.insert(nota1)
        try{
            val tarefas = dbHelper.get()

            var listView = findViewById<ListView>(R.id.my_list)

            var adapter = AdapterTask(this, tarefas)

            listView.adapter = adapter

            var tasks = findViewById<Button>(R.id.button_create_task)
            tasks.setOnClickListener{
                val intent = Intent(this, TaskCreate::class.java)
                startActivity(intent)
            }
            var api = findViewById<Button>(R.id.button_view)
            api.setOnClickListener{
                val intent = Intent(this, TaskViewAll::class.java)
                startActivity(intent)
            }
        }catch (erro: Exception){
            print("Ocorreu um erro inesperado!! $erro")
        }
    }
}