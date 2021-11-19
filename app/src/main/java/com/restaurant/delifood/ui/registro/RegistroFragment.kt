package com.restaurant.delifood.ui.registro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.kaopiz.kprogresshud.KProgressHUD
import com.restaurant.delifood.R
import com.restaurant.delifood.databinding.FragmentRegistroBinding

class RegistroFragment : Fragment(R.layout.fragment_registro) {

    private lateinit var binding : FragmentRegistroBinding
    private val viewModel : RegistroViewModel by viewModels()
    private var progress : KProgressHUD? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegistroBinding.bind(view)

        eventos()
        configurarObservables()
    }

    private fun eventos() = with(binding){
        //Botón Acción -> REGISTRARME
        btnRegistrar.setOnClickListener {
            var estado = true

            //Obtener los datos
            val nombres = edtNombres.editText?.text.toString().trim()
            val apepat = edtApePaterno.editText?.text.toString().trim()
            val apemat = edtApeMaterno.editText?.text.toString().trim()
            val correo = edtCorreo.editText?.text.toString().trim()
            val clave = edtClave.editText?.text.toString()

            //Limpiar validaciones
            edtNombres.error = ""
            edtApePaterno.error = ""
            edtApeMaterno.error = ""
            edtCorreo.error = ""
            edtClave.error = ""

            //Validar los datos
            if (nombres.isEmpty()){
                edtNombres.error = "Ingrese los nombres"
                estado = false
            }
            if (apepat.isEmpty()){
                edtApePaterno.error = "Ingrese el apellido paterno"
                estado = false
            }
            if (apemat.isEmpty()){
                edtApeMaterno.error = "Ingrese el apellido materno"
                estado = false
            }
            if (correo.isEmpty()){
                edtCorreo.error = "Ingrese el correo"
                estado = false
            }
            if (clave.isEmpty()){
                edtClave.error = "Ingrese la contraseña de su correo"
                estado = false
            }

            //Ejecutar acción
            if(estado) {
                MaterialAlertDialogBuilder(requireContext(),
                    R.style.ThemeOverlay_AppCompat)
                    .setMessage("¿Desea registrar al usuario?")
                    .setNegativeButton("Cancelar") { dialog, which ->
                        // Respond to negative button press
                    }
                    .setPositiveButton("Aceptar") { dialog, which ->
                        viewModel.registrarUsuario(correo, clave)
                        Navigation.findNavController(root).navigate(R.id.action_registroFragment_to_loginFragment)
                    }
                    .show()
                //Toast.makeText(requireContext(),"Usuario registrado correctamente", Toast.LENGTH_SHORT).show()
                //
            }
        }

        //Botón Acción -> VOLVER
        btnVolver.setOnClickListener {
            Navigation.findNavController(root).navigate(R.id.action_registroFragment_to_loginFragment)
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
            } else {
                progress?.dismiss()
            }
        })

        viewModel.error.observe(viewLifecycleOwner, Observer { error ->
            Toast.makeText(requireContext(),error, Toast.LENGTH_SHORT).show()
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        progress = null
    }
}