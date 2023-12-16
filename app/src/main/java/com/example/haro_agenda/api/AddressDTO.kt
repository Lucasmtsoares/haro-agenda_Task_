package com.example.haro_agenda.api

import com.example.haro_agenda.domain.Address

class AddressDTO (
    val cep : String,
    val state : String,
    val city : String,
    val neighborhood : String,
    val street : String,
) {

    val convertToAddress : Address
        get() = Address(
        cep = cep,
        rua = street,
        cidade = city,
        bairro =  neighborhood,
        estado = state
    )

}