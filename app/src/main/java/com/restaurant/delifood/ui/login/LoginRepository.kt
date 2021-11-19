package com.restaurant.delifood.ui.login

import com.restaurant.delifood.core.OperationResult
import com.restaurant.delifood.model.Categories

interface LoginRepository {

    //EL QUE VA A HACER EL REPOSITORIO
    suspend fun login(email:String, password:String) : OperationResult<Boolean>

}