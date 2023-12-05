package com.example.haro_agenda.navegacao.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.SearchView
import com.example.haro_agenda.Adapter
import com.example.haro_agenda.Dao.NotaDao
import com.example.haro_agenda.NotaForm
import com.example.haro_agenda.R


class NotasFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_nota_card, container, false)

        val navbutton = view.findViewById<Button>(R.id.notas)

        val dbHelper = NotaDao(requireContext())
        var notas = dbHelper.getAll()


        var listView = view.findViewById<ListView>(R.id.lista)

        var adapter = Adapter(requireContext(), notas)

        listView.adapter = adapter

        val searchView = view.findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrBlank()) {
                    val dbHelper = NotaDao(requireContext())
                    notas = dbHelper.search(query)
                    adapter = Adapter(requireContext(), notas)

                    listView.adapter = adapter

                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrBlank()) {
                    val dbHelper = NotaDao(requireContext())
                    notas = dbHelper.getAll()
                    adapter = Adapter(requireContext(), notas)

                    listView.adapter = adapter
                }

                return true
            }
        })

        navbutton.setOnClickListener {
            val intent = Intent(requireContext(), NotaForm::class.java)
            startActivity(intent)
        }
        return view
    }
}
