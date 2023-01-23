package com.emanuelgalvao.buscacep.status

enum class CepErrorStatus(val message: String) {
    INVALID_CEP("O CEP informado não foi encontrado."),
    SERVER_ERROR("Ocorreu um erro no servidor.")
}