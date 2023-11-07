package com.example.haro_agenda.navegacao.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.haro_agenda.R
import com.example.haro_agenda.TasksActivity

class TarefasFragment : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
      val view = inflater.inflate(R.layout.fragment_tarefas, container, false)

      val botaoAbrirAtividade = view.findViewById<Button>(R.id.btnAbrirNotas)
      botaoAbrirAtividade.setOnClickListener {
          // Crie um Intent para abrir a MinhaAtividade
          val intent = Intent(context, TasksActivity::class.java)

          // Inicie a MinhaAtividade
          startActivity(intent)
      }

      return view
  }

}