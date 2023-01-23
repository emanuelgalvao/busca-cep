package com.emanuelgalvao.buscacep.callback

import com.emanuelgalvao.buscacep.model.CepModel
import com.emanuelgalvao.buscacep.status.CepErrorStatus
import com.emanuelgalvao.buscacep.status.CepValidationStatus

sealed class CepCallback {
    class Error(val cepErrorStatus: CepErrorStatus): CepCallback()
    class Success(val cepModel: CepModel): CepCallback()
    class Validation(val cepValidationStatus: CepValidationStatus): CepCallback()
}
