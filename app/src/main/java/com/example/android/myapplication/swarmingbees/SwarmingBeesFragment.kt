package com.example.android.myapplication.swarmingbees

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android.myapplication.R
import com.example.android.myapplication.database.BeeDatabase
import com.example.android.myapplication.databinding.FragmentSwarmingBeesBinding

class SwarmingBeesFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentSwarmingBeesBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_swarming_bees, container, false)
        val application = requireNotNull(this.activity).application
        val arguments = SwarmingBeesFragmentArgs.fromBundle(requireArguments())
        val dataSource = BeeDatabase.getInstance(application).beeDatabaseDao
        val viewModelFactory = SwarmingBeesViewModelFactory(arguments.groupkey,dataSource)
        val swarmingBeesViewModel = ViewModelProvider(this, viewModelFactory).get(SwarmingBeesViewModel::class.java)

        binding.swarmingBeesViewModel = swarmingBeesViewModel

        binding.setLifecycleOwner(this)

        val adapter = SwarmingBeesAdapter(SwarmingBeesClickListener { beehiveId ->
            //Toast.makeText(context,"${beehiveId}",Toast.LENGTH_SHORT).show()
            swarmingBeesViewModel.onClickBeehive(beehiveId)
        })

        binding.swarmingBeesList.adapter = adapter

        swarmingBeesViewModel.beehives.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        swarmingBeesViewModel.navigateToBeeReviewFragment.observe(this, Observer { beehive ->
            beehive?.let {
                findNavController().navigate(SwarmingBeesFragmentDirections.actionSwarmingBeesToBeehiveReviewFragment(beehive,arguments.groupkey,5))
                swarmingBeesViewModel.donenavigateToBeeReviewFragment()
            }
        })

        swarmingBeesViewModel.navigateToSwarmingBeesDescriptionFragment.observe(this, Observer {
            if(it==true){
                this.findNavController().navigate(SwarmingBeesFragmentDirections.actionSwarmingBeesToBeeManagementFragment(arguments.groupkey))
                swarmingBeesViewModel.doneNavigateToSwarmingBeesDescriptionFragment()
            }
        })

        swarmingBeesViewModel.navigateToManagementFragment.observe(this, Observer {
            if(it==true){
                this.findNavController().navigate(SwarmingBeesFragmentDirections.actionSwarmingBeesToBeeManagementFragment(arguments.groupkey))
                swarmingBeesViewModel.doneNavigateToManagementFragment()
            }
        })

        val manager = GridLayoutManager(activity, 1, GridLayoutManager.VERTICAL, false)
        binding.swarmingBeesList.layoutManager = manager

        return binding.root
    }
}