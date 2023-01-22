package com.emanuelgalvao.buscacep.callback

import com.emanuelgalvao.buscacep.model.CepModel
import com.emanuelgalvao.buscacep.status.ErrorStatus
import com.emanuelgalvao.buscacep.status.ValidationStatus

sealed class CepCallback {
    class Error(val errorStatus: ErrorStatus): CepCallback()
    class Success(val cepModel: CepModel): CepCallback()
    class Validation(val validationStatus: ValidationStatus): CepCallback()
}
