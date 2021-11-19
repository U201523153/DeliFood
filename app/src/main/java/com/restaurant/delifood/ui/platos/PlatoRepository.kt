package com.restaurant.delifood.ui.platos

import com.restaurant.delifood.core.OperationResult
import com.restaurant.delifood.model.Plato

interface PlatoRepository {

    suspend fun getDishes(token:String,uuid:String) : OperationResult<List<Plato>>
}