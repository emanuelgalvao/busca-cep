package com.emanuelgalvao.buscacep.utils

import com.emanuelgalvao.buscacep.status.ValidationStatus

class Validator {

    companion object {

        val instance: Validator by lazy {
            Validator()
        }
    }

    fun validateCep(cep: String): ValidationStatus {

        return when (false) {
            isNotEmpty(cep) -> ValidationStatus.CEP_EMPTY
            isNotNull(cep) -> ValidationStatus.CEP_NULL
            correctLength(cep) -> ValidationStatus.CEP_INCORRECT_LENGTH
            containsOnlyNumbers(cep) -> ValidationStatus.CEP_CONTAINS_LETTERS
            else -> {
                ValidationStatus.CEP_VALID
            }
        }
    }

    fun isNotEmpty(cep: String): Boolean {
        return cep.isNotEmpty()
    }

    fun isNotNull(cep: String?): Boolean {
        return cep != null
    }

    fun correctLength(cep: String): Boolean {
        return cep.length == 8
    }

    fun containsOnlyNumbers(cep: String): Boolean {
        return cep.all { it.isDigit() }
    }
}