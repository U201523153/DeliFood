package com.restaurant.delifood.data

import android.app.Person
import android.content.Context
import android.util.Log
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.restaurant.delifood.model.Categories
import com.restaurant.delifood.model.Persona
import com.restaurant.delifood.model.Plato
import com.restaurant.delifood.model.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.withContext
import java.util.*

/* import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.* */

//Singleton
object Api {
    //1. CONFIGURAR FIREBASE
    private lateinit var auth: FirebaseAuth
    private lateinit var fbDatabase: FirebaseDatabase
    private lateinit var dbReference: DatabaseReference

    /*interface ApiInterface {
        suspend fun logIn(): Boolean
    }*/

    //Autenticacion Basica
    suspend fun login(email: String, clave: String): Boolean {
        auth = FirebaseAuth.getInstance()
        var ok = false

        withContext(Dispatchers.IO) {
            auth.signInWithEmailAndPassword(email, clave)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("LOGIN", "signInWithEmailAndPassword:success")
                        val user = auth.currentUser
                        ok = true
                    } else {
                        Log.w("LOGIN", "signInWithEmailAndPassword:failure", task.exception)
                    }
                }
        }
        return ok
    }

    fun registrar(email:String,clave:String): Boolean {
        auth = FirebaseAuth.getInstance()
        var ok = false

        auth.createUserWithEmailAndPassword(email, clave)
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    Log.d("LOGIN","signInWithEmailAndPassword:success")
                    val user = auth.currentUser
                    Log.d("USUARIO", user.toString())
                    ok = true
                } else {
                    Log.w("LOGIN","signInWithEmailAndPassword:failure", task.exception)
                }
            }

        return ok
    }

    fun reestablecer(email: String): Boolean{
        auth = FirebaseAuth.getInstance()
        var ok = false

        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    ok = true
                }
            }

        return ok
    }

    fun registrarUsuario(p: Persona): Boolean{
        //persona._id = UUID.randomUUID().toString()
        //FirebaseApp.initializeApp(this)
        fbDatabase = FirebaseDatabase.getInstance()
        dbReference = fbDatabase.getReference()
        dbReference.child("persona").child(p._id).setValue(p)


        return true
    }
}