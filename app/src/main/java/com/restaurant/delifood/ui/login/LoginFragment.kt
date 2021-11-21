package com.restaurant.delifood.ui.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.restaurant.delifood.R
import com.restaurant.delifood.databinding.FragmentLoginBinding
import com.kaopiz.kprogresshud.KProgressHUD
import com.restaurant.delifood.data.AppDatabase
import com.restaurant.delifood.data.AppDatabase.Companion.instancia
import com.restaurant.delifood.model.Plato
import com.restaurant.delifood.model.PlatoDto
import com.restaurant.delifood.model.Usuario
import com.restaurant.delifood.model.UsuarioDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel : LoginViewModel by viewModels()
    private var progress : KProgressHUD? = null

    private lateinit var auth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        eventos()
    }

    private fun eventos() = with(binding){
        //Hyperlink Acción -> REESTABLECER
        tvReestablecer.setOnClickListener {
            val correo = edtEmail.editText?.text.toString().trim()
            viewModel.reestablecerContrasena(correo)
        }

        //Boton Acción -> REGISTRARME
        btnRegistrarme.setOnClickListener {
            Navigation.findNavController(root).navigate(R.id.action_loginFragment_to_registroFragment)
        }

        //Boton Acción -> INGRESAR
        btnIngresar.setOnClickListener {
            var estado = true

            //Obtener los datos
            val correo = edtEmail.editText?.text.toString().trim()
            val clave = edtPassword.editText?.text.toString()

            edtEmail.error = ""
            edtPassword.error = ""

            //Validar los datos
            if (correo.isEmpty()) {
                edtEmail.error = "Ingrese el email"
                estado = false
            }
            if (clave.isEmpty()) {
                edtPassword.error = "Ingrese la contraseña"
                estado = false
            }

            //Ejecutar acción
            if (estado) {
                //viewModel.autenticar(correo,clave)
                auth = FirebaseAuth.getInstance()
                auth.signInWithEmailAndPassword(correo, clave)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            //val user = auth.currentUser
                            Navigation.findNavController(root).navigate(R.id.action_loginFragment_to_menuFragment)
                        } else {
                            Toast.makeText(requireContext(),"Usuario y/o contraseña incorrecto", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        progress = null
    }

}