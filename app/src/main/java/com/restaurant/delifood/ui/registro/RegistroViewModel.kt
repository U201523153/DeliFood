package com.restaurant.delifood.ui.registro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.restaurant.delifood.data.Api
import com.restaurant.delifood.model.Usuario
import kotlinx.coroutines.*

class RegistroViewModel : ViewModel() {

    private var _loader : MutableLiveData<Boolean> = MutableLiveData()
    val loader : LiveData<Boolean> = _loader
    private var _error : MutableLiveData<String> = MutableLiveData()
    val error : LiveData<String> = _error

    fun registrarUsuario(correo: String, clave: String) {
        viewModelScope.launch {

            _loader.value = true

            try{
                val response = withContext(Dispatchers.IO){
                    Api.registrar(correo,clave)
                }

            }catch (ex:Exception){
                _error.value = ex.message
            }finally {
                _loader.value = false
            }
        }
    }

}