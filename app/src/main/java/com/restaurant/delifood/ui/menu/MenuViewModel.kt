package com.restaurant.delifood.ui.menu

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.*
import com.restaurant.delifood.model.Categories
import kotlinx.coroutines.launch

class MenuViewModel : ViewModel() {

    var repository: MenuRepositoryImp? = null

    private var _loader: MutableLiveData<Boolean> = MutableLiveData()
    val loader: LiveData<Boolean> = _loader

    private var _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error

    private val _categorias = MutableLiveData<List<Categories>>()
    val categorias: LiveData<List<Categories>> = _categorias

    private lateinit var fbDatabase: FirebaseDatabase
    private lateinit var dbReference: DatabaseReference

    init {
        repository = MenuRepositoryImp()
    }

    fun obtenerCategorias() {

        //Dispatcher Main por default
        viewModelScope.launch {

            _loader.value = true

            try {
                /*val response = withContext(Dispatchers.IO) {
                    repository?.getCategories()
                }

                when (response) {
                    is OperationResult.Complete -> {
                        _categorias.value = response.data
                    }
                    is OperationResult.Error -> {
                        _error.value = response.excepcion
                    }
                }*/

                val postListener = object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        var cat:List<Categories> = listOf()

                        for(item in dataSnapshot.children){
                            var c: Categories? = item.getValue(Categories::class.java)
                            //cat.add(c)
                        }
                        _categorias.value = cat
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        // Getting Post failed, log a message
                        Log.w("CATEGORIA", "loadPost:onCancelled", databaseError.toException())
                    }
                }
                dbReference.child("categorias").addValueEventListener(postListener)

            } catch (ex: Exception) {
                _error.value = ex.message
            } finally {
                _loader.value = false
            }
        }
    }
}