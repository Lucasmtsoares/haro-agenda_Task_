package com.example.haro_agenda

import androidx.fragment.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.haro_agenda.databinding.GetCepBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.haro_agenda.domain.Address
import com.example.haro_agenda.service.AddressService
import com.example.haro_agenda.api.RetrofitHelper
import kotlinx.coroutines.CoroutineScope
import android.view.inputmethod.InputMethodManager
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.cardview.widget.CardView

//import com.example.haro_agenda.databinding.FormNotaBinding
class GetAddress: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        val binding = inflater.inflate(R.layout.get_cep, container, false)


        val button = binding.findViewById<Button>(R.id.botao)


        button.setOnClickListener{
            var cep = binding.findViewById<EditText>(R.id.editTextCep).text.toString()
            var address : Address?
            val scope = MainScope()
            try {
                scope.launch {

                    withContext(Dispatchers.IO) {
                        address =
                            AddressService(RetrofitHelper().addressApi()).findAddressByCep(cep)
                    }
                    if (address != null) {
                        binding.findViewById<TextView>(R.id.rua).setText(address?.rua)
                        binding.findViewById<TextView>(R.id.bairro).setText(" - ${address?.bairro}")
                        binding.findViewById<TextView>(R.id.cidade)
                            .setText("${address?.cidade}/${address?.estado}")
                        binding.findViewById<TextView>(R.id.cep).setText(" - ${address?.cep}")
                        binding.findViewById<TextView>(R.id.mensagemDeErro).visibility =
                            View.INVISIBLE
                        binding.findViewById<CardView>(R.id.cardView).visibility = View.VISIBLE


                    } else {
                        binding.findViewById<TextView>(R.id.mensagemDeErro).visibility =
                            View.VISIBLE
                    }
                    fechar_teclado()

                }
            }catch (erro: Exception){
                print(erro)
            }



        }

        return binding

    }
    private fun fechar_teclado(){
        val view: View? = activity?.currentFocus

        if (view != null) {
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
}


}