package com.restaurant.delifood.ui.login

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.restaurant.delifood.core.OperationResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginRepositoryImp : LoginRepository {
    private lateinit var auth: FirebaseAuth

    override suspend fun login(email: String, password: String): Boolean {
        var estado = false
        try{
            auth = FirebaseAuth.getInstance()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("LOGIN", "signInWithEmailAndPassword:success")
                        val user = auth.currentUser
                        estado = true
                    } else {
                        Log.w("LOGIN", "signInWithEmailAndPassword:failure", task.exception)
                    }
                }
        }catch (ex:Exception){
            //OperationResult.Error(ex.message)
        } finally {
            return estado
        }
    }
}