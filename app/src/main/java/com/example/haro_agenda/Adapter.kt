package com.example.haro_agenda

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView


class Adapter(val context: Context, val nota: List<Nota>) : BaseAdapter() {
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


        var nome = rowViewCard.findViewById<TextView>(R.id.nameTextView)
        var desc = rowViewCard.findViewById<TextView>(R.id.descriptionTextView)
        var data = rowViewCard.findViewById<TextView>(R.id.dateTextView)


        nome.text = nota[position].nome
        desc.text = nota[position].descricao
        data.text = nota[position].data




        return rowViewCard
    }
}