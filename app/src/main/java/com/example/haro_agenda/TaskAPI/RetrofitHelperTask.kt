package com.example.haro_agenda.TaskAPI

import com.example.haro_agenda.TaskAPI.domainTask.AddressTask
import com.example.haro_agenda.api.AddressApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitHelperTask {
    companion object {
        val BASE_URL = "https://brasilapi.com.br/"
    }

    private fun buildRetrofit() = Retrofit.Builder()
        .baseUrl(Companion.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    fun addressTask() : AddressTask = buildRetrofit().create(AddressTask::class.java)

}