package com.example.haro_agenda.navegacao.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.haro_agenda.R

class NotasFragment : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
      return inflater.inflate(R.layout.nota_card, container, false)
  }

override fun onDestroyView() {
        super.onDestroyView()
    }
}