package com.restaurant.delifood.ui.cheff

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.restaurant.delifood.R
import com.restaurant.delifood.databinding.FragmentCheffBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class CheffFragment : Fragment(R.layout.fragment_cheff) {


    private lateinit var binding: FragmentCheffBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCheffBinding.bind(view)


        binding.mapViewRestaurante.onCreate(savedInstanceState)
        binding.mapViewRestaurante.onResume()
        MapsInitializer.initialize(requireContext())
        binding.mapViewRestaurante.getMapAsync { googleMap ->

            val position = LatLng(-12.0889097,-77.0365457)

            val camPos = CameraPosition.Builder()
                .target(position)
                .zoom(15f)
                .bearing(90f)
                .build()

            val cameraUpdate = CameraUpdateFactory.newCameraPosition(camPos)

            googleMap.animateCamera(cameraUpdate)

            googleMap.uiSettings.isZoomControlsEnabled = true

            googleMap.addMarker(MarkerOptions().position(position).title("Restaurante"))

        }
    }
}