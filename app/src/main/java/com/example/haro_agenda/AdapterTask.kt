package com.example.haro_agenda

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.haro_agenda.models.TasksClass


class AdapterTask(val context: Context, val task: List<TasksClass>) : BaseAdapter() {
    override fun getCount(): Int {
        return task.size
    }

    override fun getItem(position: Int): Any {
        return task[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {


        val inflater = LayoutInflater.from(context)


        val rowViewCard = inflater.inflate(R.layout.task_card, parent, false)


        try {
            var nome = rowViewCard.findViewById<TextView>(R.id.task_check)
            var desc = rowViewCard.findViewById<TextView>(R.id.task_tag)
            var edit = rowViewCard.findViewById<ImageView>(R.id.task_edit)


            nome.text = task[position].nome
            desc.text = task[position].descricao
            edit.setImageResource(task[position].edit)



        }catch (erro_adapter: Exception){
            print("Não foi possivel encontrar uma referencia!! $erro_adapter")
        }
        return rowViewCard
    }
}