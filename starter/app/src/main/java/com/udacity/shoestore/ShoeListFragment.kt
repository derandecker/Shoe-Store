package com.udacity.shoestore

import android.app.ActionBar
import android.os.Bundle
import android.util.TypedValue
import android.view.*
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.marginStart
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe
import kotlinx.android.synthetic.main.fragment_shoe_list.*
import timber.log.Timber


class ShoeListFragment : Fragment() {

    private val sharedShoeListViewModel: SharedShoeListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentShoeListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_list,
            container, false
        )
        binding.newShoeFab.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_shoeListFragment_to_shoeDetailFragment)
        )

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedShoeListViewModel.shoeList.observe(viewLifecycleOwner, Observer { shoe ->
            shoe?.forEach {

                val shoeNameTextView: TextView = TextView(context)
                val companyNameTextView: TextView = TextView(context)
                val sizeTextView: TextView = TextView(context)
                val descriptionTextView: TextView = TextView(context)
                val separatorView: View = View(context)

                shoeNameTextView.text = it.name
                companyNameTextView.text = it.company
                sizeTextView.text = it.size.toString()
                descriptionTextView.text = it.description

                shoe_list.addView(shoeNameTextView)
                shoe_list.addView(companyNameTextView)
                shoe_list.addView(sizeTextView)
                shoe_list.addView(descriptionTextView)


                val viewParams: ViewGroup.LayoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, resources.getDimensionPixelSize(R.dimen.one_dp))
                separatorView.layoutParams = viewParams
                separatorView.setBackgroundColor(resources.getColor(R.color.black))
                shoe_list.addView(separatorView)


            }
        })
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }


}