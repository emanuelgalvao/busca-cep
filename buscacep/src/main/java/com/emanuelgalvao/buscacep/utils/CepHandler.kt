package com.emanuelgalvao.buscacep.utils

class CepHandler private constructor() {

    companion object {
        val instance: CepHandler by lazy {
            CepHandler()
        }
    }

    fun formatCepToCorrectFormat(cep: String): String {
        var cepFormatted = cep

        cepFormatted = removeDashs(cepFormatted)
        cepFormatted = removeSpaces(cepFormatted)
        cepFormatted = removeDots(cepFormatted)

        return cepFormatted
    }

    fun removeDashs(cep: String): String {
        return cep.replace("-", "")
    }

    fun removeSpaces(cep: String): String {
        return cep.replace(" ", "")
    }

    fun removeDots(cep: String): String {
        return cep.replace(".", "")
    }
}