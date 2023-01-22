package com.emanuelgalvao.buscacep.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emanuelgalvao.buscacep.SearchCep
import com.emanuelgalvao.buscacep.callback.CepCallback
import com.emanuelgalvao.buscacep.model.CepModel
import com.emanuelgalvao.buscacep.status.ErrorStatus
import com.emanuelgalvao.buscacep.status.ValidationStatus
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _requesting: MutableLiveData<Boolean> = MutableLiveData()
    val requesting: LiveData<Boolean>
        get() = _requesting

    private val _success: MutableLiveData<CepModel> = MutableLiveData()
    val success: LiveData<CepModel>
        get() = _success

    private val _error: MutableLiveData<ErrorStatus> = MutableLiveData()
    val error: LiveData<ErrorStatus>
        get() = _error

    private val _validation: MutableLiveData<ValidationStatus> = MutableLiveData()
    val validation: LiveData<ValidationStatus>
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
                   handleError(callback.errorStatus)
               }
               is CepCallback.Validation -> {
                   handleValidation(callback.validationStatus)
               }
           }
           _requesting.postValue(false)
       }
    }

    private fun handleSuccess(cepModel: CepModel) {
        _success.postValue(cepModel)
    }

    private fun handleError(errorStatus: ErrorStatus) {
        _error.postValue(errorStatus)
    }

    private fun handleValidation(validationStatus: ValidationStatus) {
        _validation.postValue(validationStatus)
    }
}