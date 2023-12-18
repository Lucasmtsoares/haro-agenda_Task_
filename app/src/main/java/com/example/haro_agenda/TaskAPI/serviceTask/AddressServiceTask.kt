package com.example.haro_agenda.TaskAPI.serviceTask

import com.example.haro_agenda.TaskAPI.AdressTaskAPI
import com.example.haro_agenda.TaskAPI.domainTask.AddressTask
import com.example.haro_agenda.api.AddressApi
import com.example.haro_agenda.domain.Address

class AddressServiceTask(
    private val addressTask: AdressTaskAPI,
) {

    fun findAddressByCep(cep: String): AddressTask? {
        return addressTask.obterCEP(cep)
            .execute()
            .body()?.let { addressDTOTask ->
                addressDTOTask.convertToAddress
            }
    }

}