package com.restaurant.delifood.ui.platos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.restaurant.delifood.R
import com.restaurant.delifood.databinding.FragmentPlatosBinding
import com.kaopiz.kprogresshud.KProgressHUD

class PlatosFragment : Fragment(R.layout.fragment_platos) {

    private lateinit var binding : FragmentPlatosBinding

    private lateinit var adapter : PlatoAdapter

    private val viewModel : PlatoViewModel by viewModels()

    private var progress : KProgressHUD? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPlatosBinding.bind(view)

        setupAdapter()
        eventos()
        cargarDatos()
        setupObservables()

    }

    private fun eventos() = with(binding) {

        btnFavoritos.setOnClickListener {
            //Listado de Favoritos en la BD Local
            viewModel.obtenerFavoritos()
        }

        btnMenu.setOnClickListener {
            //Listado de Platos desde el servicio
            cargarDatos()
        }

    }

    private fun setupObservables() = with(binding) {

        viewModel.loader.observe(viewLifecycleOwner,  { condicion ->

            //if(condicion) progressBar.visibility = View.VISIBLE
            //else progressBar.visibility = View.GONE

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

        viewModel.error.observe(viewLifecycleOwner,  { error ->
            Toast.makeText(requireContext(),error, Toast.LENGTH_SHORT).show()
        })

        viewModel.platos.observe(viewLifecycleOwner,  { platos ->
            adapter.actualizarLista(platos)
        })
    }

    private fun cargarDatos() {

        val token = requireContext()
            .getSharedPreferences("PREFERENCES_TOKEN",0)
            .getString("KEY_TOKEN","") ?: ""

        val id = requireContext()
            .getSharedPreferences("PREFERENCES_CATEGORIA",0)
            .getString("KEY_ID","") ?: ""

        viewModel.obtenerDatos2(token,id)
    }

    private fun setupAdapter() = with(binding) {

        adapter = PlatoAdapter()
        rvPlatos.adapter = adapter

        adapter.onClickPlato = { plato ->

            val direction = PlatosFragmentDirections.actionPlatosFragmentToDetallePlatoFragment(plato)
            Navigation.findNavController(root).navigate(direction)

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        progress = null
    }

}