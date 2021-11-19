package com.restaurant.delifood.ui.platos

import com.restaurant.delifood.core.OperationResult
import com.restaurant.delifood.data.Api
import com.restaurant.delifood.model.Plato

class PlatoRepositoryImp : PlatoRepository {


    override suspend fun getDishes(token:String,uuid:String): OperationResult<List<Plato>> {

        return try{

            /*val response = Api.build().getDishes(token,uuid)

            if(response.isSuccessful){
                OperationResult.Complete(response.body())
            }else{
                OperationResult.Error(response.message())
            }*/

            OperationResult.Complete(null)

        }catch (ex:Exception){
            OperationResult.Error(ex.message)
        }
    }
}