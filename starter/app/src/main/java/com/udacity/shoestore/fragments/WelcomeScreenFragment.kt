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
import com.udacity.shoestore.databinding.FragmentWelcomeScreenBinding
import com.udacity.shoestore.viewmodels.WelcomeScreenViewModel
import timber.log.Timber

class WelcomeScreenFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeScreenBinding
    private lateinit var welcomeScreenViewModel: WelcomeScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_welcome_screen, container, false)
        welcomeScreenViewModel = ViewModelProvider(this)[WelcomeScreenViewModel::class.java]
        binding.welcomeScreenViewModel = welcomeScreenViewModel
        binding.lifecycleOwner = this

        welcomeScreenViewModel.eventNavigateToInstructionsScreen.observe(viewLifecycleOwner) { isNavigatingToInstructionsScreen ->
            if (isNavigatingToInstructionsScreen)
                navigateToInstructionsScreen()
        }

        return binding.root
    }

    private fun navigateToInstructionsScreen() {
        Timber.i("Navigating to Instructions Screen")
        findNavController().navigate(WelcomeScreenFragmentDirections.actionWelcomeScreenFragmentToInstructionsFragment())
        welcomeScreenViewModel.resetNavigationStatus()
    }

}