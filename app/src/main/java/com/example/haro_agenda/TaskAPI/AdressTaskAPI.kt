package com.example.haro_agenda.TaskAPI

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AdressTaskAPI {
    @GET("{cep}/json/")
    fun obterCEP(@Path("cepTask") cep: String): Call<AddressDTOTask>
}