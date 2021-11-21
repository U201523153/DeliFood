package com.restaurant.delifood.ui.platos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.restaurant.delifood.core.OperationResult
import com.restaurant.delifood.data.Api
import com.restaurant.delifood.data.AppDatabase.Companion.instancia
import com.restaurant.delifood.model.Plato
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlatoViewModel : ViewModel() {

    var repository : PlatoRepository? = null

    private val _loader = MutableLiveData<Boolean>()
    val loader : LiveData<Boolean> = _loader

    private val _error = MutableLiveData<String>()
    val error : LiveData<String> = _error

    private val _platos = MutableLiveData<List<Plato>>()
    val platos : LiveData<List<Plato>> = _platos

    init{
        repository = PlatoRepositoryImp()
    }

    fun obtenerDatos2(token: String, id: String) {

        viewModelScope.launch {

            _loader.value = true

            try{
                val response = withContext(Dispatchers.IO){
                    repository?.getDishes(token,id)
                }

                when(response){
                    is OperationResult.Complete -> {
                        _platos.value = response.data
                    }
                    is OperationResult.Error -> {
                        _error.value = response.excepcion
                    }
                }

            }catch (ex:Exception){
                _error.value = ex.message
            }finally {
                _loader.value = false
            }

        }
    }

    fun obtenerDatos(token: String, id: String) {

        viewModelScope.launch {

            _loader.value = true

            try{
                /*val response = withContext(Dispatchers.IO){
                    Api.build().getDishes(token,id)
                }
                if(response.isSuccessful){
                    _platos.value = response.body()
                }else{
                    _error.value = response.message()
                }*/

            }catch (ex:Exception){
                _error.value = ex.message
            }finally {
                _loader.value = false
            }

        }
    }

    fun obtenerFavoritos() {

        viewModelScope.launch {

            _loader.value = true

            try{
                val response = withContext(Dispatchers.IO){
                    instancia?.platoDao()?.obtenerPlatos()
                }

                response?.let { platosDto ->

                    //Convertir de PLATODTO -> PLATO
                    val platos = platosDto.map {
                        Plato(it._id,it.avatar,it.category_id,it.description,it.name,it.price,0,0.0,0.0,.0,.0,.0,.0)
                    }
                    _platos.value = platos
                }

            }catch (ex:Exception){
                _error.value = ex.message
            }finally {
                _loader.value = false
            }

        }

    }
}