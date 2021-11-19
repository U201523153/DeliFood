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

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel : LoginViewModel by viewModels()
    private var progress : KProgressHUD? = null

    private lateinit var auth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        eventos()
        configurarObservables()
    }

    private fun eventos() = with(binding){
        //Hyperlink Acción -> REESTABLECER
        tvReestablecer.setOnClickListener {
            val correo = edtEmail.editText?.text.toString().trim()
            viewModel.reestablecerContrasena(correo)
        }

        //Boton Acción -> INGRESAR
        btnIngresar.setOnClickListener {
            var estado = true

            //Obtener los datos
            val correo = edtEmail.editText?.text.toString().trim()
            val clave = edtPassword.editText?.text.toString()

            //Validar los datos
            when {
                correo.isEmpty() -> {
                    edtEmail.error = "Ingrese el email"
                    estado = false
                }
                else -> {
                    edtEmail.error = ""
                }
            }

            when {
                clave.isEmpty() -> {
                    edtPassword.error = "Ingrese la contraseña"
                    estado = false
                }
                else -> {
                    edtPassword.error = ""
                }
            }

            //Ejecutar acción
            if (estado) {
                //viewModel.autenticar(correo,clave)
                auth = FirebaseAuth.getInstance()
                    auth.signInWithEmailAndPassword(correo, clave)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Log.d("LOGIN", "signInWithEmailAndPassword:success")
                                val user = auth.currentUser

                                Navigation.findNavController(root).navigate(R.id.action_loginFragment_to_menuFragment)
                            } else {

                                Log.w("LOGIN", "signInWithEmailAndPassword:failure", task.exception)
                            }
                        }
            }

        }

        //Boton Acción -> REGISTRARME
        btnRegistrarme.setOnClickListener {
            Navigation.findNavController(root).navigate(R.id.action_loginFragment_to_registroFragment)
        }
    }

    private fun configurarObservables() = with(binding) {

        viewModel.loader.observe(viewLifecycleOwner, Observer { condicion ->
            if(condicion){
                progress = KProgressHUD.create(requireContext())
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setLabel("Por favor, espere...")
                    .setCancellable(false)
                    .setAnimationSpeed(2)
                    .setDimAmount(0.5f)
                    .show()
            }else{
                progress?.dismiss()
            }
        })

        viewModel.error.observe(viewLifecycleOwner, Observer { error ->
            Toast.makeText(requireContext(),error, Toast.LENGTH_SHORT).show()
        })

        viewModel.usuario.observe(viewLifecycleOwner, Observer { usuario ->
            usuario?.let{
                /*requireContext().getSharedPreferences("PREFERENCES_TOKEN",0).edit().apply {
                    putString("KEY_TOKEN",it.token).apply()
                }*/
                Navigation.findNavController(root).navigate(R.id.action_loginFragment_to_menuFragment)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        progress = null
    }

}