package com.restaurant.delifood.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.Navigation
import com.restaurant.delifood.R


class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val SPLASH_TIME_OUT : Long = 3000

    //override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
    //    return inflater.inflate(R.layout.fragment_splash, container, false)
    //}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_loginFragment)
        },SPLASH_TIME_OUT)

    }


}