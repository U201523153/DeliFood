package com.restaurant.delifood.ui.detalle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.restaurant.delifood.data.AppDatabase.Companion.instancia
import com.restaurant.delifood.model.PlatoDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlatoDetalleViewModel : ViewModel() {

    private val _loader = MutableLiveData<Boolean>()
    val loader : LiveData<Boolean> = _loader

    private val _error = MutableLiveData<String>()
    val error : LiveData<String> = _error

    private val _response = MutableLiveData<String>()
    val response : LiveData<String> = _response


    fun grabarFavoritos(dto: PlatoDto) {

        viewModelScope.launch {

            _loader.value = true

            try{
                val response = withContext(Dispatchers.IO){
                    instancia?.platoDao()?.insert(dto)
                }

                if(response?.toInt()!! > 0){
                    _response.value = "Plato agregado"
                }else{
                    _error.value = "No pudimos agregarlo a favoritos. Intentelo mas tarde"
                }

            }catch (ex:Exception){
                _error.value = ex.message
            }finally {
                _loader.value = false
            }

        }
    }


}