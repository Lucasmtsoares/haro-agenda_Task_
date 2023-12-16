package com.example.haro_agenda.service

import com.example.haro_agenda.api.AddressApi
import com.example.haro_agenda.domain.Address

class AddressService(
    private val addressApi: AddressApi,
) {

    fun findAddressByCep(cep: String): Address? {
        return addressApi.findAddressByCep(cep)
            .execute()
            .body()?.let { addressDto ->
                addressDto.convertToAddress
            }
    }

}