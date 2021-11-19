package com.restaurant.delifood.ui.menu

import com.restaurant.delifood.core.OperationResult
import com.restaurant.delifood.model.Categories

interface MenuRepostory {

    suspend fun getCategories() : OperationResult<List<Categories>>

}