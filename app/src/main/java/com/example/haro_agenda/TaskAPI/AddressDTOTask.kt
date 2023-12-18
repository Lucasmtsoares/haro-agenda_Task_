package com.example.haro_agenda.TaskAPI

import com.example.haro_agenda.TaskAPI.domainTask.AddressTask

class AddressDTOTask (
    val cep : String,
    val state : String,
    val city : String,
    val neighborhood : String,
    val street : String,
) {

    val convertToAddress : AddressTask get() = AddressTask(
        cep = cep,
        rua = street,
        cidade = city,
        bairro =  neighborhood,
        estado = state
    )

}