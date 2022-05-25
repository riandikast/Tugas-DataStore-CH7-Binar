package com.binar.andika

import UserManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.asLiveData
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.common.GooglePlayServicesUtil
import com.google.android.gms.security.ProviderInstaller


class SplashScreenFragment : Fragment() {

    lateinit var userManager : UserManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_splash_screen, container, false)
        userManager = UserManager(requireContext())
        Handler().postDelayed({
            userManager.userLogin.asLiveData().observe(requireActivity()){
                if (it.equals("false")){
                    view.findNavController().navigate(R.id.action_splashScreenFragment_to_loginFragment,null,
                        NavOptions.Builder()
                            .setPopUpTo(R.id.splashScreenFragment,
                                true).build())

                }else if (it.equals("true")){
                    view.findNavController().navigate(R.id.action_splashScreenFragment_to_homeFragment,null,
                        NavOptions.Builder()
                            .setPopUpTo(R.id.splashScreenFragment,
                                true).build())
                }
            }
        }, 2000)


        return view
    }

}