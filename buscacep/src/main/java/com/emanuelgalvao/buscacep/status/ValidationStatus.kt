package com.emanuelgalvao.buscacep.status

enum class ValidationStatus(val message: String) {
    CEP_NULL("O CEP informado não pode ser nulo."),
    CEP_EMPTY("O CEP informado não pode ser vazio."),
    CEP_CONTAINS_LETTERS("O CEP informado contem letras ou caracteres especiais."),
    CEP_INCORRECT_LENGTH("O CEP informado deve possuir 8 digitos."),
    CEP_VALID("O CEP é válido.")
}