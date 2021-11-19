package com.restaurant.delifood.ui.menu

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.restaurant.delifood.MenuPrincipalActivity
import com.restaurant.delifood.R
import com.restaurant.delifood.databinding.FragmentMenuBinding


class MenuFragment : Fragment(R.layout.fragment_menu) {

    private lateinit var binding: FragmentMenuBinding

    private val viewModel : MenuViewModel by viewModels()

    private lateinit var adaptador: CategoriaAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMenuBinding.bind(view)

        init()
        configurarAdaptador()
        configurarObservables()
    }

    private fun configurarObservables() {

        viewModel.loader.observe(viewLifecycleOwner, Observer { condicion ->

            if(condicion) binding.progressBar.visibility = View.VISIBLE
            else binding.progressBar.visibility = View.GONE
        })

        viewModel.error.observe(viewLifecycleOwner, Observer { error ->
            Toast.makeText(requireContext(),error,Toast.LENGTH_SHORT).show()
        })

        viewModel.categorias.observe(viewLifecycleOwner, Observer { categorias ->
            adaptador.actualizarLista(categorias)
        })
    }

    private fun init() {

        val token = requireContext()
            .getSharedPreferences("PREFERENCES_TOKEN",0)
            .getString("KEY_TOKEN","") ?: ""

        viewModel.obtenerCategorias()
    }

    private fun configurarAdaptador() = with(binding) {

        adaptador = CategoriaAdapter(){ categoria ->

            requireContext().getSharedPreferences("PREFERENCES_CATEGORIA",0)
                .edit().putString("KEY_ID",categoria._id).apply()

            startActivity(Intent(requireContext(), MenuPrincipalActivity::class.java))
        }

        rvCategories.adapter = adaptador
    }

}