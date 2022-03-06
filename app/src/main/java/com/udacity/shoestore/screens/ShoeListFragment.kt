package com.udacity.shoestore.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ViewShoeBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewmodel.ShoesViewModel

class ShoeListFragment : Fragment() {

    private val viewModel: ShoesViewModel by activityViewModels()
    private lateinit var binding: FragmentShoeListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_list,
            container,
            false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setHasOptionsMenu(true)

        binding.fab.setOnClickListener {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }

        viewModel.shoesList.observe(viewLifecycleOwner, Observer { shoesList ->
            for (shoe in shoesList) {
                addShoeToView(container, shoe)
            }
        })

        return binding.root
    }

    private fun addShoeToView(
        container: ViewGroup?,
        shoe: Shoe
    ) {
        val shoeBinding: ViewShoeBinding = DataBindingUtil.inflate(
            layoutInflater, R.layout.view_shoe, container, false
        )
        shoeBinding.shoe = shoe
        binding.shoeListLinearLayout.addView(shoeBinding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
        return true
    }
}