package com.restaurant.delifood.ui.login

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.restaurant.delifood.core.OperationResult
import com.restaurant.delifood.data.Api
import com.restaurant.delifood.model.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel : ViewModel() {

    private var _loader : MutableLiveData<Boolean> = MutableLiveData()
    val loader : LiveData<Boolean> = _loader
    private var _error : MutableLiveData<String> = MutableLiveData()
    val error : LiveData<String> = _error

    private val _usuario = MutableLiveData<Usuario>()
    val usuario : LiveData<Usuario> = _usuario

    var repository: LoginRepository? = null

    init {
        repository = LoginRepositoryImp()
    }

    fun autenticar(correo: String, clave: String) {

        viewModelScope.launch {

            _loader.value = true
            try{
                val response = withContext(Dispatchers.IO){
                    repository?.login(correo,clave)
                }

                when(response){
                    is OperationResult.Complete -> {
                        if (response.data == true) {
                            Log.d("autenticar","OK|Credencial correcta")
                        } else {
                            Log.d("autenticar","OK|Credenciales incorrectas")
                        }
                    }

                    is OperationResult.Error -> {
                        Log.d("autenticar","Ha ocurrido un error al momento de llamar Firebase")
                        _error.value = "Ha ocurrido un error al momento de llamar Firebase"
                    }
                }
            }catch (ex:Exception){
                _error.value = ex.message
            }finally {
                _loader.value = false
            }
        }
    }

    fun reestablecerContrasena(correo: String) {
        viewModelScope.launch {

            _loader.value = true

            try{
                val response = withContext(Dispatchers.IO){
                    if (Api.reestablecer(correo)) {
                        _usuario.value = null
                    } else {
                        _error.value = null
                    }
                }

                /*val response = withContext(Dispatchers.IO){
                    Api.login(correo,clave)
                }
                if(response.isSuccessful){
                    _usuario.value = response.body()
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

}