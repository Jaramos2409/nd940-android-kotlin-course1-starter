package com.udacity.shoestore.fragments

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.viewmodels.ListOfShoesViewModel
import com.udacity.shoestore.viewmodels.ShoeListFragmentViewModel
import timber.log.Timber

class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding
    private lateinit var shoeListFragmentViewModel: ShoeListFragmentViewModel
    private val listOfShoesViewModel: ListOfShoesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)
        shoeListFragmentViewModel = ViewModelProvider(this)[ShoeListFragmentViewModel::class.java]
        binding.shoeListFragmentViewModel = shoeListFragmentViewModel
        binding.lifecycleOwner = this

        val shoeListFragmentArgs by navArgs<ShoeListFragmentArgs>()

        shoeListFragmentArgs.shoeToAdd.let { shoe ->
            if (shoe != null) {
                listOfShoesViewModel.addShoeToList(shoe)
            }
        }

        shoeListFragmentViewModel.eventNavigateToLoginScreen.observe(viewLifecycleOwner) { isNavigatingToLoginScreen ->
            if (isNavigatingToLoginScreen)
                navigateToLoginScreen()
        }

        shoeListFragmentViewModel.eventNavigateToShoeDetailScreen.observe(viewLifecycleOwner) { isNavigatingToShoeDetailScreen ->
            if (isNavigatingToShoeDetailScreen)
                navigateToShoeDetailScreen()
        }

        listOfShoesViewModel.listOfShoes.observe(viewLifecycleOwner) { newShoe ->
            newShoe.forEach { shoe ->
                val textView = TextView(activity)
                textView.text = getString(
                    R.string.shoe_list_shoe_details,
                    shoe.name,
                    shoe.company,
                    shoe.size,
                    shoe.description
                )
                binding.shoeListLinearLayout.addView(textView)
            }

        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(
            object : MenuProvider {
                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                    menuInflater.inflate(R.menu.logout_menu, menu)
                }

                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    return when (menuItem.itemId) {
                        R.id.logout -> shoeListFragmentViewModel.navigateToLoginScreen()
                        else -> false
                    }
                }
            }, viewLifecycleOwner, Lifecycle.State.RESUMED
        )
    }

    private fun navigateToLoginScreen() {
        Timber.i("Navigating to Login Screen")
        findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
        shoeListFragmentViewModel.resetNavigationToLoginScreenStatus()
    }

    private fun navigateToShoeDetailScreen() {
        Timber.i("Navigating to Shoe Detail Screen")
        findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        shoeListFragmentViewModel.resetNavigationToShoeDetailScreenStatus()
    }
}