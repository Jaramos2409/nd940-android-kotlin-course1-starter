package com.udacity.shoestore.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.viewmodels.LoginViewModel
import timber.log.Timber

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        binding.loginViewmodel = loginViewModel
        binding.lifecycleOwner = this

        loginViewModel.eventNavigateToWelcomeScreen.observe(viewLifecycleOwner) { isNavigatingToWelcomeScreen ->
            if (isNavigatingToWelcomeScreen)
                navigateToTheWelcomeScreen()
        }

        return binding.root
    }

    private fun navigateToTheWelcomeScreen() {
        Timber.i("Navigating to Welcome Screen")
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeScreenFragment())
        loginViewModel.resetNavigationStatus()
    }
}