package com.example.haro_agenda.navegacao.ui.pastas

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.haro_agenda.Dao.PastaDAO
import com.example.haro_agenda.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class ListarPastasFragment : Fragment(), PastaClickListener {

  val listaPastas  = mutableListOf<PastaModel>()
  var idPastaAtual = -1

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
      val context = requireContext()
      val pastaDAO = PastaDAO(context)
      val view = inflater.inflate(R.layout.fragment_listar_pastas, container, false)

      val textoCaminho: TextView = view.findViewById<TextView>(R.id.text_notifications)
      textoCaminho.text = ">Início"

      val recyclerView: RecyclerView = view.findViewById(R.id.folderRecyclerView)

      // Define o GridLayoutManager com 2 colunas por linha
      val layoutManager = GridLayoutManager(context, 2)
      recyclerView.layoutManager = layoutManager

      // Listar pastas do banco de dados
      for (pasta in pastaDAO.listarPastas(idParaFiltrar = -1))
          listaPastas.add(pasta)

      val adapter = PastaAdapter(listaPastas, context, pastaDAO, textoCaminho, this)
      recyclerView.adapter = adapter

      val fabAddFolder: FloatingActionButton = view.findViewById(R.id.fabAddFolder)
      fabAddFolder.setOnClickListener {
          exibirDialogNovaPasta(view, recyclerView, pastaDAO)
      }

      return view
  }

    private fun exibirDialogNovaPasta(view: View, recyclerView: RecyclerView, pastaDAO: PastaDAO) {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_nova_pasta, null)

        val nomePastaEditText: EditText = dialogView.findViewById(R.id.nomePastaEditText)

        builder.setView(dialogView)
        builder.setTitle("Nova Pasta")
        builder.setPositiveButton("Criar") { _, _ ->
            val nomeNovaPasta = nomePastaEditText.text.toString()

            // Criar uma nova PastaModel com o nome inserido
            val novaPasta = PastaModel(
                nome = nomeNovaPasta,
                dataUltimaModificacao = "Última modificação: HOJE",
                corIcone = "#FF5733"
            )

            if (idPastaAtual > -1)
                novaPasta.idEncadeado = idPastaAtual

            // Adicionar a nova pasta à lista de pastas
            listaPastas.add(novaPasta)

            pastaDAO.salvarPasta(novaPasta)

            Snackbar.make(
                view.findViewById(R.id.pastaConstraintLayout),
                "Nova pasta criada: ${novaPasta.nome}",
                Snackbar.LENGTH_SHORT
            ).show()

            // Notificar o adaptador de que os dados foram alterados
            recyclerView.adapter?.notifyDataSetChanged()
        }
        builder.setNegativeButton("Cancelar") { _, _ -> }
        builder.create().show()
    }

    override fun onPastaClicked(pastaId: Int) {
        // Atualize a variável idPastaAtual aqui
        idPastaAtual = pastaId
    }
}