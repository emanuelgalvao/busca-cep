package com.emanuelgalvao.buscacep.utils

import com.emanuelgalvao.buscacep.status.CepValidationStatus

internal class Validator private constructor() {

    companion object {
        val instance: Validator by lazy {
            Validator()
        }
    }

    fun validateCep(cep: String): CepValidationStatus {

        return when (false) {
            isNotEmpty(cep) -> CepValidationStatus.CEP_EMPTY
            isNotNull(cep) -> CepValidationStatus.CEP_NULL
            correctLength(cep) -> CepValidationStatus.CEP_INCORRECT_LENGTH
            containsOnlyNumbers(cep) -> CepValidationStatus.CEP_CONTAINS_LETTERS
            else -> {
                CepValidationStatus.CEP_VALID
            }
        }
    }

    fun isNotEmpty(cep: String): Boolean = cep.isNotEmpty()

    fun isNotNull(cep: String?): Boolean = cep != null

    fun correctLength(cep: String): Boolean = cep.length == 8

    fun containsOnlyNumbers(cep: String): Boolean = cep.all { it.isDigit() }
}