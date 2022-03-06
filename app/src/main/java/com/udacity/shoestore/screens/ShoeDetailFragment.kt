package com.udacity.shoestore.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.viewmodel.ShoeDetailViewModel
import com.udacity.shoestore.viewmodel.ShoesViewModel

class ShoeDetailFragment : Fragment() {

    private val shoesViewModel: ShoesViewModel by activityViewModels()
    private lateinit var detailViewModel: ShoeDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentShoeDetailBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_detail,
            container,
            false)

        detailViewModel = ViewModelProvider(this)[ShoeDetailViewModel::class.java]
        binding.viewModel = detailViewModel
        binding.lifecycleOwner = this

        binding.cancelButton.setOnClickListener {
            navigateToShoeList()
        }

        binding.saveButton.setOnClickListener {
            if (detailViewModel.validateFields()) {
                val shoe = detailViewModel.buildShoe()
                shoesViewModel.addShoe(shoe)
                navigateToShoeList()
            } else {
                Toast.makeText(context, getString(R.string.empty_fields_warning), Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    private fun navigateToShoeList() {
        findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
    }
}