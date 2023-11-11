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
import com.example.haro_agenda.databinding.TaskCardBinding
import com.example.haro_agenda.models.TasksClass
import android.graphics.drawable.ColorDrawable
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.app.NotificationCompat.getColor


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

            when (tag.text) {
                "Trabalho" -> tag.setBackgroundColor(context.getColor(R.color.teal_700))
                "Evento" -> tag.setBackgroundColor(context.getColor(R.color.purple_500))
                "Pessoal" -> tag.setBackgroundColor(context.getColor(R.color.purple_200))
                "Escola" -> tag.setBackgroundColor(context.getColor(R.color.green_500))
            }

            edit.setOnClickListener {

                PopupMenu(context, edit).apply {
                    menuInflater.inflate(R.menu.poup_task, this.menu)
                    show()
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.update_task -> {

                                val intent = Intent(context, TaskUpdate::class.java)
                                intent.putExtra("id", task[position].id)
                                intent.putExtra("descricao", task[position].descricao)
                                intent.putExtra("tag", task[position].tag)
                                context.startActivity(intent)
                                true
                            }
                            R.id.delete_task -> {

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