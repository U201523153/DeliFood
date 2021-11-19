package com.restaurant.delifood.ui.detalle

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.restaurant.delifood.R
import com.restaurant.delifood.databinding.DialogOrdenarBinding
import com.restaurant.delifood.databinding.FragmentDetallePlatoBinding
import com.restaurant.delifood.model.Plato
import com.restaurant.delifood.model.PlatoDto
import com.squareup.picasso.Picasso

class DetallePlatoFragment : Fragment(R.layout.fragment_detalle_plato) {

    private lateinit var binding: FragmentDetallePlatoBinding

    private lateinit var plato : Plato

    private val viewModel : PlatoDetalleViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetallePlatoBinding.bind(view)

        init()
        eventos()
        setupObservables()
    }

    private fun setupObservables() = with(binding) {

        viewModel.loader.observe(viewLifecycleOwner,  { condicion ->

            if(condicion) progressBar.visibility = View.VISIBLE
            else progressBar.visibility = View.GONE
        })

        viewModel.error.observe(viewLifecycleOwner,  { error ->
            Toast.makeText(requireContext(),error, Toast.LENGTH_SHORT).show()
        })

        viewModel.response.observe(viewLifecycleOwner,  { response ->
            Toast.makeText(requireContext(),response, Toast.LENGTH_SHORT).show()
        })
    }

    private fun eventos() = with(binding) {

        btnOrdenar.setOnClickListener {
            mostrarDialogo().show()
        }

        btnPreferidos.setOnClickListener {

            //PLATO -> DTO
            val dto = PlatoDto(
                plato._id,
                plato.avatar,
                plato.category_id,
                plato.description,
                plato.name,
                plato.price,
            )

            viewModel.grabarFavoritos(dto)
        }
    }

    private fun mostrarDialogo(): AlertDialog {

        val binding = DialogOrdenarBinding.inflate(LayoutInflater.from(requireContext()))
        val builder = AlertDialog.Builder(requireContext())

        builder.setView(binding.root)

        val alertDialog = builder.create()
        alertDialog.setCancelable(false)

        binding.btnSumar.setOnClickListener {
            val numero = binding.tvCantidad.text.toString().toInt() + 1
            binding.tvCantidad.text = "$numero"
        }

        binding.btnRestar.setOnClickListener {
            if (binding.tvCantidad.text.toString().toInt() > 0) {
                val numero = binding.tvCantidad.text.toString().toInt() - 1
                binding.tvCantidad.text = "$numero"
            }
        }

        binding.btnConfirmar.setOnClickListener {
            alertDialog.dismiss()
        }

        return alertDialog

    }

    private fun init() = with(binding) {

        arguments?.let { bundle ->

            plato = DetallePlatoFragmentArgs.fromBundle(bundle).plato

            tvNombrePlatoDetalle.text = plato.name
            tvDescripcionPlatoDetallew6.text = plato.description
            tvPrecioPlatoDetalle.text = plato.price.toString()

            Picasso.get()
                .load("https://firebasestorage.googleapis.com/v0/b/restaurant-administrador-am.appspot.com/o/${plato.avatar}?alt=media&token=ffa42398-b21c-49f2-99ea-af4bf15504f9")
                .into(imgPlatoDetalle)

        }

    }


}