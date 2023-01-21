package com.example.android.myapplication.queenbeemanagement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android.myapplication.R
import com.example.android.myapplication.database.BeeDatabase
import com.example.android.myapplication.databinding.FragmentQueenbeeManagementBinding

class QueenBeeManagementFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentQueenbeeManagementBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_queenbee_management,container,false)

        val application = requireNotNull(this.activity).application
        val dataSource = BeeDatabase.getInstance(application).beeDatabaseDao
        val arguments = QueenBeeManagementFragmentArgs.fromBundle(requireArguments())
        val viewModelFactory = QueenBeeManagementViewModelFactory(arguments.groupkey, dataSource)

        val queenBeeManagementViewModel = ViewModelProvider(this, viewModelFactory).get(QueenBeeManagementViewModel::class.java)

        binding.queenBeeManagementViewModel = queenBeeManagementViewModel

        val adapter = QueenBeeManagementAdapter(QueenBeeManagementListener { beehivesId ->
            //Toast.makeText(context, "${beehivesId}",Toast.LENGTH_SHORT).show()
            queenBeeManagementViewModel.onClickBeehive(beehivesId)
        })

        binding.queenBeeList.adapter = adapter

        queenBeeManagementViewModel.beehives.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
        binding.setLifecycleOwner(this)


        queenBeeManagementViewModel.navigateToReviewFragment.observe(this, Observer { beehive ->
            beehive?.let {
                this.findNavController().navigate(QueenBeeManagementFragmentDirections.actionQueenbeeManagementToBeehiveReviewFragment(beehive,arguments.groupkey,1))
                queenBeeManagementViewModel.doneNavigateToReviewFragment()
            }
        })

        queenBeeManagementViewModel.navigateToManagementFragment.observe(this,Observer{
            if(it==true){
                this.findNavController().navigate(QueenBeeManagementFragmentDirections.actionQueenbeeManagementToBeeManagementFragment(arguments.groupkey))
                queenBeeManagementViewModel.doneNavigateToManagementFragment()
            }
        })

        val manager = GridLayoutManager(activity,1, GridLayoutManager.VERTICAL,false)
        binding.queenBeeList.layoutManager = manager

        return binding.root
    }
}