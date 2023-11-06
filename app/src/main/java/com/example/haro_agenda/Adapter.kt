package com.example.haro_agenda

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import com.example.haro_agenda.models.Nota
import com.example.haro_agenda.Dao.NotaDao


class Adapter(val context: Context, val nota: MutableList<Nota>) : BaseAdapter() {
    override fun getCount(): Int {
        return nota.size
    }

    override fun getItem(position: Int): Any {
        return nota[position]
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


        val rowViewCard = inflater.inflate(R.layout.nota_card, parent, false)
        val card = rowViewCard.findViewById<CardView>(R.id.cardView)

        var nome = rowViewCard.findViewById<TextView>(R.id.nameTextView)
        var desc = rowViewCard.findViewById<TextView>(R.id.descriptionTextView)



        nome.text = nota[position].nome
        desc.text = nota[position].descricao


        val cardView = rowViewCard.findViewById<ImageView>(R.id.delete)
        cardView.setOnClickListener {
            val dbHelper = NotaDao(context)
            dbHelper.delete(nota[position])
            nota.removeAt(position)
            notifyDataSetChanged()
        }
        val edit = rowViewCard.findViewById<ImageView>(R.id.edit)
        edit.setOnClickListener{
            val intent = Intent(context, NotaEdit::class.java)
            val bundle = Bundle()
            bundle.putParcelable("nota", nota[position])

            intent.putExtras(bundle)
            context.startActivity(intent)

        }




        return rowViewCard
    }
}