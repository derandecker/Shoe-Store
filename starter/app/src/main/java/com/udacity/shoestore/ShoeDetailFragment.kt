package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import timber.log.Timber

class ShoeDetailFragment : Fragment() {

    private val sharedShoeListViewModel: SharedShoeListViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentShoeDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_detail,
            container, false
        )

        binding.saveButton.setOnClickListener {
            sharedShoeListViewModel.updateShoeList(
                binding.shoeNameEdittext.text.toString(),
               binding.shoeSizeEdittext.text.toString().toDouble(),
                binding.companyEdittext.text.toString(),
                binding.descriptionEdittext.text.toString()
            )

            it.findNavController().navigate(R.id.action_shoeDetailFragment_pop)
        }

        binding.cancelButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_shoeDetailFragment_pop)
        }
        return binding.root
    }

}