package com.example.haro_agenda

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.haro_agenda.TaskAPI.domainTask.AddressTask
import com.example.haro_agenda.api.RetrofitHelper
import com.example.haro_agenda.domain.Address
import com.example.haro_agenda.service.AddressService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TaskViewAll: AppCompatActivity() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        val binding = inflater.inflate(R.layout.api_task, container, false)


        val button = binding.findViewById<Button>(R.id.botao)


        button.setOnClickListener{
            var cep = binding.findViewById<EditText>(R.id.editTextCep).text.toString()
            var address : AddressTask?
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



                    } else {
                       //ikhkh
                    }

                }
            }catch (erro: Exception){
                print(erro)
            }



        }

        return binding

    }



}