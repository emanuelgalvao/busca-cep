package com.emanuelgalvao.buscacep.callback

import com.emanuelgalvao.buscacep.model.CepModel

sealed class CepCallback {
    class Error(): CepCallback()
    class Success(cep: CepModel): CepCallback()
}
