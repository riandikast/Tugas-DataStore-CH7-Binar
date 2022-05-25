package com.binar.andika

import UserManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.asLiveData
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.android.synthetic.main.logout_dialog.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ProfileFragment : Fragment() {

    lateinit var userManager : UserManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view  = inflater.inflate(R.layout.fragment_profile, container, false)
        userManager = UserManager(requireContext())
        userManager.userName.asLiveData().observe(viewLifecycleOwner){
            view.name.text = "Name : $it"
        }
        userManager.userUsername.asLiveData().observe(viewLifecycleOwner){
            view.username.text = "Username : $it"
        }
        userManager.userUmur.asLiveData().observe(viewLifecycleOwner){
            view.umur.text = "Umur : $it"
        }
        userManager.userAddress.asLiveData().observe(viewLifecycleOwner){
            view.address.text = "Address : $it"
        }
        userManager.userImage.asLiveData().observe(viewLifecycleOwner){
            Glide.with(requireContext()).load( it ).into(view.pp2)
        }





        view.btnlogout.setOnClickListener {
            val custom = LayoutInflater.from(requireContext()).inflate(R.layout.logout_dialog, null)
            val a = AlertDialog.Builder(requireContext())
                .setView(custom)
                .create()

            custom.btnlogouttidak.setOnClickListener {
                a.dismiss()
            }

            custom.btnlogoutya.setOnClickListener {

                GlobalScope.launch {
                    userManager.deleteDataLogin()
                    userManager.deleteDataUser()

                }
                a.dismiss()
                view.findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
            }
            a.show()
        }
        return view
    }

}