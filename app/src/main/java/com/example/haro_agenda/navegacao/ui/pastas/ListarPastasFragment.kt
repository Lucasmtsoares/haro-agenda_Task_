package com.example.haro_agenda.navegacao.ui.pastas

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.haro_agenda.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListarPastasFragment : Fragment() {

  val listaPastas = mutableListOf<PastaModel>()
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
      val context = requireContext()
      val view = inflater.inflate(R.layout.fragment_listar_pastas, container, false)

      val recyclerView: RecyclerView = view.findViewById(R.id.folderRecyclerView)

      // Define o GridLayoutManager com 2 colunas por linha
      val layoutManager = GridLayoutManager(context, 2)
      recyclerView.layoutManager = layoutManager

      criaListaDePastas()
      val adapter = PastaAdapter(listaPastas, context)
      recyclerView.adapter = adapter

      val fabAddFolder: FloatingActionButton = view.findViewById(R.id.fabAddFolder)
      fabAddFolder.setOnClickListener {
          exibirDialogNovaPasta(recyclerView)
      }

      return view
  }
    // Crie uma lista de pastas (FolderModel) manualmente ou de outra forma.
    private fun criaListaDePastas() {

        listaPastas.add(PastaModel(1, "Pasta 1", "01/01/2023", "#FF0000"))
        listaPastas.add(PastaModel(2, "Pasta 2", "02/01/2023","#00FF00"))
        listaPastas.add(PastaModel(3, "Pasta 3", "03/01/2023", "#0000FF"))
        listaPastas.add(PastaModel(4, "Pasta 4", "04/01/2023", "#FFFFFF"))

    }
    private fun exibirDialogNovaPasta(recyclerView: RecyclerView) {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_nova_pasta, null)

        val nomePastaEditText: EditText = dialogView.findViewById(R.id.nomePastaEditText)

        builder.setView(dialogView)
        builder.setTitle("Nova Pasta")
        builder.setPositiveButton("Criar") { _, _ ->
            val nomeNovaPasta = nomePastaEditText.text.toString()

            // Criar uma nova PastaModel com o nome inserido
            val novaPasta = PastaModel(0, nomeNovaPasta, "Última modificação: HOJE", "#FF5733")

            // Adicionar a nova pasta à lista de pastas
            listaPastas.add(novaPasta)

            // Notificar o adaptador de que os dados foram alterados
            recyclerView.adapter?.notifyDataSetChanged()
        }
        builder.setNegativeButton("Cancelar") { _, _ -> }
        builder.create().show()
    }
}