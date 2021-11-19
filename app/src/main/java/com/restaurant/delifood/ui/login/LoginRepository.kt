package com.restaurant.delifood.ui.login

import com.restaurant.delifood.core.OperationResult
import com.restaurant.delifood.model.Categories

interface LoginRepository {

    suspend fun login(email:String, password:String) : Boolean

}