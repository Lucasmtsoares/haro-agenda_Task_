package com.example.haro_agenda

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import com.example.haro_agenda.Dao.TaskDAO
import com.example.haro_agenda.models.TasksClass


class AdapterTask(val context: Context, val task: MutableList<TasksClass>) : BaseAdapter() {
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
            var desc = rowViewCard.findViewById<TextView>(R.id.task_check)
            var tag = rowViewCard.findViewById<TextView>(R.id.task_tag)
            var edit = rowViewCard.findViewById<ImageView>(R.id.task_edit)


            desc.text = task[position].descricao
            tag.text = task[position].tag
            edit.setImageResource(task[position].edit)

            edit.setOnClickListener {
                // O código para manipular o clique do item da lista vai aqui
                // Por exemplo, abrir uma nova atividade ou
                //val intent = Intent(context, TasksActivityUpdate::class.java)
                //startActivity(context, intent, null)
                PopupMenu(context, edit).apply {
                    menuInflater.inflate(R.menu.poup_task, this.menu)
                    show()
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.update_task -> {
                                // Ação quando o item "Atualizar" é clicado
                                val intent = Intent(context, TaskUpdate::class.java)
                                intent.putExtra("id", task[position].id)
                                intent.putExtra("descricao", task[position].descricao)
                                intent.putExtra("tag", task[position].tag)
                                context.startActivity(intent)
                                true
                            }
                            R.id.delete_task -> {
                                // Ação quando o item "Deletar" é clicado
                                // Implemente a lógica de exclusão aqui
                                val dbHelper = TaskDAO(context)
                                dbHelper.delete(task[position])
                                task.removeAt(position)
                                notifyDataSetChanged()
                                true
                            }
                            else -> false
                        }
                    }
                }
            }



        }catch (erro_adapter: Exception){
            print("Não foi possivel encontrar uma referencia!! $erro_adapter")
        }
        return rowViewCard
    }
}