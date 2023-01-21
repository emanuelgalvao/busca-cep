package com.emanuelgalvao.buscacep.callback

import com.emanuelgalvao.buscacep.model.CepModel
import com.emanuelgalvao.buscacep.status.ValidationStatus

sealed class CepCallback {
    class Error(): CepCallback()
    class Success(cep: CepModel): CepCallback()
    class Validation(status: ValidationStatus): CepCallback()
}
