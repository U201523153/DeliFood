package com.restaurant.delifood.ui.login

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.restaurant.delifood.core.OperationResult
import com.restaurant.delifood.data.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRepositoryImp : LoginRepository {
    private lateinit var auth: FirebaseAuth

    override suspend fun login(email: String, password: String): OperationResult<Boolean> {
        return try{
            auth = FirebaseAuth.getInstance()
            OperationResult.Complete(true)
            /*withContext(Dispatchers.IO) {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d("LOGIN", "signInWithEmailAndPassword:success")
                            val user = auth.currentUser
                            OperationResult.Complete(true)
                        } else {
                            Log.w("LOGIN", "signInWithEmailAndPassword:failure", task.exception)
                            OperationResult.Complete(false)
                        }
                    }
            }*/
        }catch (ex:Exception){
            OperationResult.Error(ex.message)
        }
    }
}