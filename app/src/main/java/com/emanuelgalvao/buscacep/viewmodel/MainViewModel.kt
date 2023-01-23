package com.emanuelgalvao.buscacep.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emanuelgalvao.buscacep.SearchCep
import com.emanuelgalvao.buscacep.callback.CepCallback
import com.emanuelgalvao.buscacep.model.CepModel
import com.emanuelgalvao.buscacep.status.CepErrorStatus
import com.emanuelgalvao.buscacep.status.CepValidationStatus
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _requesting: MutableLiveData<Boolean> = MutableLiveData()
    val requesting: LiveData<Boolean>
        get() = _requesting

    private val _success: MutableLiveData<CepModel> = MutableLiveData()
    val success: LiveData<CepModel>
        get() = _success

    private val _error: MutableLiveData<CepErrorStatus> = MutableLiveData()
    val error: LiveData<CepErrorStatus>
        get() = _error

    private val _validation: MutableLiveData<CepValidationStatus> = MutableLiveData()
    val validation: LiveData<CepValidationStatus>
        get() = _validation

    fun searchCep(cep: String) {

       viewModelScope.launch {
           _requesting.postValue(true)
           val callback = SearchCep.instance.getCepData(cep)
           when (callback) {
               is CepCallback.Success -> {
                   handleSuccess(callback.cepModel)
               }
               is CepCallback.Error -> {
                   handleError(callback.cepErrorStatus)
               }
               is CepCallback.Validation -> {
                   handleValidation(callback.cepValidationStatus)
               }
           }
           _requesting.postValue(false)
       }
    }

    private fun handleSuccess(cepModel: CepModel) {
        _success.postValue(cepModel)
    }

    private fun handleError(cepErrorStatus: CepErrorStatus) {
        _error.postValue(cepErrorStatus)
    }

    private fun handleValidation(cepValidationStatus: CepValidationStatus) {
        _validation.postValue(cepValidationStatus)
    }
}