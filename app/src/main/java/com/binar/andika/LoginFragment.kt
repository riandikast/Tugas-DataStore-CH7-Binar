package com.binar.andika

import UserManager
import android.graphics.Color
import android.os.Bundle
import android.util.Log

import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch



class LoginFragment : Fragment() {

    lateinit var viewModel : ViewModelUser
    lateinit var username: String
    lateinit var password: String
    lateinit var toast : String
    lateinit var userManager : UserManager
    lateinit var dataUser : List<GetAllUserItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)



        val login = view.findViewById<Button>(R.id.btnlogin)



        getDataUserItem()
        login.setOnClickListener {
            if (loginemail.text.isNotEmpty() && loginpassword.text.isNotEmpty()){
                username = loginemail.text.toString()
                password = loginpassword.text.toString()

                check()


            }
            else{
                toast = "Harap Isi Semua Data"
                customToast()
            }
        }
        return view
    }
    fun getDataUserItem() {
        viewModel = ViewModelProvider(this).get(ViewModelUser::class.java)
        viewModel.getLiveUserObserver().observe(viewLifecycleOwner, Observer {
            dataUser = it
        })
        viewModel.userApi()
    }

    fun check() {
        userManager = UserManager(requireContext())

        for (i in dataUser.indices) {
            if (username == dataUser[i].username && password == dataUser[i].password) {
                GlobalScope.launch {

                    userManager.saveDataLogin("true")
                    userManager.saveDataUser(dataUser[i].id,  dataUser[i].umur.toString(), dataUser[i].password, dataUser[i].username, dataUser[i].name, dataUser[i].address, dataUser[i].image)

                }

                view?.findNavController()
                    ?.navigate(R.id.action_loginFragment_to_homeFragment)
                break
            }
        }
    }
    fun customToast(){
        val text = toast
        val toast = Toast.makeText(
            requireActivity()?.getApplicationContext(),
            text,
            Toast.LENGTH_LONG
        )
        val text1 =
            toast.getView()?.findViewById(android.R.id.message) as TextView
        val toastView: View? = toast.getView()
        toastView?.setBackgroundColor(Color.TRANSPARENT)
        text1.setTextColor(Color.RED);
        text1.setTextSize(15F)
        toast.show()
        toast.setGravity(Gravity.CENTER or Gravity.TOP, 0, 960)
    }




}