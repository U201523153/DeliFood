package com.restaurant.delifood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.restaurant.delifood.databinding.ActivityMenuPrincipalBinding

class MenuPrincipalActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMenuPrincipalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgMenu.setOnClickListener {
            binding.drawer.openDrawer(GravityCompat.START)
        }

        val naviController = Navigation.findNavController(this,R.id.nav_host_fragment_menu_principal)
        NavigationUI.setupWithNavController(binding.navigationView,naviController)


        naviController.addOnDestinationChangedListener(object : NavController.OnDestinationChangedListener{

            override fun onDestinationChanged(controller: NavController, destination: NavDestination, arguments: Bundle?) {

                when (destination.label) {
                    "fragment_platos" -> {
                        binding.tvTitulo.text = "MENU"
                    }
                    "fragment_detalle_plato" -> {
                        binding.tvTitulo.text = "DETALLE"
                    }
                    else -> {
                        binding.tvTitulo.text = "CHEFF"
                    }
                }

            }


        })

    }
}