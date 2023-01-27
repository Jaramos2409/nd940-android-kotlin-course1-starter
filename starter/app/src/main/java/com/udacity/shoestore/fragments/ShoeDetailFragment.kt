package com.udacity.shoestore.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.viewmodels.ShoeDetailViewModel
import timber.log.Timber

class ShoeDetailFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailBinding
    private lateinit var shoeDetailViewModel: ShoeDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)
        shoeDetailViewModel = ViewModelProvider(this)[ShoeDetailViewModel::class.java]
        binding.shoeDetailViewModel = shoeDetailViewModel
        binding.lifecycleOwner = this

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                checkFieldsForEmptyValues()
            }

            override fun afterTextChanged(p0: Editable?) {}
        }

        binding.shoeNameTextInputEditText.addTextChangedListener(textWatcher)
        binding.companyTextInputEditText.addTextChangedListener(textWatcher)
        binding.shoeSizeTextInputEditText.addTextChangedListener(textWatcher)
        binding.descriptionTextInputEditText.addTextChangedListener(textWatcher)

        shoeDetailViewModel.eventCancelAndNavigateToShoeListScreen.observe(viewLifecycleOwner) { isCancellingAndNavigatingToShoeListScreen ->
            if (isCancellingAndNavigatingToShoeListScreen)
                cancelAndNavigateToShoeListScreen()
        }

        shoeDetailViewModel.eventSaveAndNavigateToShoeListScreen.observe(viewLifecycleOwner) { isSavingAndNavigatingToShoeListScreen ->
            if (isSavingAndNavigatingToShoeListScreen)
                saveAndNavigateToShoeListScreen()
        }

        return binding.root
    }

    private fun cancelAndNavigateToShoeListScreen() {
        Timber.i("Navigating to Shoe List Screen")
        findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
        shoeDetailViewModel.resetCancelNavigationStatus()
    }

    private fun saveAndNavigateToShoeListScreen() {
        Timber.i("Navigating to Shoe List Screen")

        findNavController().navigate(
            ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment(
                shoeDetailViewModel.getShoeDetailsInsideOfShoeModel()
            )
        )
        shoeDetailViewModel.resetSaveNavigationStatus()
    }

    private fun checkFieldsForEmptyValues() {
        binding.saveButton.isEnabled = !(binding.shoeNameTextInputEditText.text.toString().isEmpty()
                || binding.shoeSizeTextInputEditText.text.toString().isEmpty()
                || binding.companyTextInputEditText.text.toString().isEmpty()
                || binding.descriptionTextInputEditText.text.toString().isEmpty())
    }
}
