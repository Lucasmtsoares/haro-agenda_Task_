package com.example.haro_agenda.navegacao.ui.pastas

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.haro_agenda.R

class PastaAdapter(private val folderList: List<PastaModel>, private val context: Context) :
    RecyclerView.Adapter<PastaAdapter.PastaViewHolder>() {

    inner class PastaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pastaIconeImageView: ImageView = itemView.findViewById(R.id.pastaIconeImageView)
        val pastaNomeTextView: TextView = itemView.findViewById(R.id.pastaNomeTextView)
        val pastaDataModificacaoTextView: TextView = itemView.findViewById(R.id.pastaDataModificacaoTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PastaViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_pasta_bloco, parent, false)
        return PastaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PastaViewHolder, position: Int) {
        val pasta = folderList[position]
        holder.pastaNomeTextView.text = pasta.nome
        holder.pastaDataModificacaoTextView.text = pasta.dataUltimaModificacao
        holder.pastaIconeImageView.setColorFilter(Color.parseColor(pasta.corIcone))
    }

    override fun getItemCount(): Int {
        return folderList.size
    }
}
