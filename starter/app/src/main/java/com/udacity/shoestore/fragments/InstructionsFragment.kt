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
import com.udacity.shoestore.databinding.FragmentInstructionsBinding
import com.udacity.shoestore.viewmodels.InstructionsViewModel
import timber.log.Timber

class InstructionsFragment : Fragment() {

    private lateinit var binding: FragmentInstructionsBinding
    private lateinit var instructionsViewModel: InstructionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_instructions, container, false)
        instructionsViewModel = ViewModelProvider(this)[InstructionsViewModel::class.java]
        binding.instructionsViewModel = instructionsViewModel
        binding.lifecycleOwner = this

        instructionsViewModel.eventNavigateToShoeListScreen.observe(viewLifecycleOwner) { isNavigatingToShoeScreen ->
            if (isNavigatingToShoeScreen)
                navigateToShoeListScreen()
        }

        return binding.root
    }

    private fun navigateToShoeListScreen() {
        Timber.i("Navigating to Instructions Screen")
        findNavController().navigate(InstructionsFragmentDirections.actionInstructionsFragmentToShoeListFragment())
        instructionsViewModel.resetNavigationStatus()
    }
}