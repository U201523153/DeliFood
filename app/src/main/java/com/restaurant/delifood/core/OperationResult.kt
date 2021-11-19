package com.restaurant.delifood.core

sealed class OperationResult<out T> {

    //Complete
    data class Complete<T>(val data:T?) : OperationResult<T>()

    //Error
    data class Error(val excepcion:String?) : OperationResult<Nothing>()

    //Loading
    object Loading : OperationResult<Nothing>()

}