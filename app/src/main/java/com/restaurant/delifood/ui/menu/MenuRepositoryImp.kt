package com.restaurant.delifood.ui.menu

import com.google.firebase.database.FirebaseDatabase
import com.restaurant.delifood.core.OperationResult
import com.restaurant.delifood.data.Api
import com.restaurant.delifood.model.Categories

class MenuRepositoryImp : MenuRepostory {

    override suspend fun getCategories(): OperationResult<List<Categories>> {

        return try{

            OperationResult.Complete(null)
            //val response = Api.build().getCategories(token)

            /*if(response.isSuccessful){
                OperationResult.Complete(response.body())
            }else{
                OperationResult.Error(response.message())
            }*/
        }catch (ex:Exception){
            OperationResult.Error(ex.message)
        }

    }


}