package com.example.android.myapplication.broodframebalancing

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
import com.example.android.myapplication.databinding.FragmentBroodFrameBalancingBinding
import com.example.android.myapplication.queenbeemanagement.QueenBeeManagementFragmentDirections

class BroodFrameBalancingFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentBroodFrameBalancingBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_brood_frame_balancing, container, false)

        val application = requireNotNull(this.activity).application

        val arguments = BroodFrameBalancingFragmentArgs.fromBundle(requireArguments())
        val dataSource = BeeDatabase.getInstance(application).beeDatabaseDao
        val viewModelFactory = BroodFrameBalancingViewModelFactory(arguments.groupkey,dataSource)
        val broodFrameViewModel = ViewModelProvider(this, viewModelFactory).get(BroodFrameBalancingViewModel::class.java)

        binding.broodFrameViewModel = broodFrameViewModel

        val adapter = BroodFrameBalancingAdapter(BroodFrameBalancingClickListener { beehiveId ->
            //Toast.makeText(context,"${beehiveId}",Toast.LENGTH_SHORT).show()
            broodFrameViewModel.onClickBeehive(beehiveId)
        })

        binding.broodFrameList.adapter = adapter

        broodFrameViewModel.beehives.observe(viewLifecycleOwner, Observer{
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.setLifecycleOwner(this)

        broodFrameViewModel.navigateToBeeReviewFragment.observe(this, Observer { beehive ->
            beehive?.let {
                findNavController().navigate(BroodFrameBalancingFragmentDirections.actionBroodframeBalancingToBeehiveReviewFragment(beehive,arguments.groupkey,2))
                broodFrameViewModel.donenavigateToBeeReviewFragment()
            }
        })

        broodFrameViewModel.navigateToBeeManagementFragment.observe(this,Observer{
            if(it==true){
                this.findNavController().navigate(BroodFrameBalancingFragmentDirections.actionBroodframeBalancingToBeeManagementFragment(arguments.groupkey))
                broodFrameViewModel.doneNavigateToBeeManagementFragment()
            }
        })

        val manager = GridLayoutManager(activity, 1, GridLayoutManager.VERTICAL, false)
        binding.broodFrameList.layoutManager = manager

        return binding.root
    }
}