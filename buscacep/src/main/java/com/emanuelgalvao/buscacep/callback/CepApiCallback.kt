package com.emanuelgalvao.buscacep.callback

import com.emanuelgalvao.buscacep.model.CepModel

interface CepApiCallback {
    fun success(data: CepModel)
    fun error()
}
