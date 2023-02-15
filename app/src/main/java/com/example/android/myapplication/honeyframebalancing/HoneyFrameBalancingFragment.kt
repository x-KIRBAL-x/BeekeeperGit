package com.example.android.myapplication.honeyframebalancing

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
import com.example.android.myapplication.databinding.FragmentHoneyFrameBalancingBinding

class HoneyFrameBalancingFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentHoneyFrameBalancingBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_honey_frame_balancing, container, false)

        val application = requireNotNull(this.activity).application

        val arguments = HoneyFrameBalancingFragmentArgs.fromBundle(requireArguments())
        val dataSource = BeeDatabase.getInstance(application).beeDatabaseDao
        val viewModelFactory = HoneyFrameBalancingViewModelFactory(arguments.groupkey,dataSource)
        val honeyFrameVewModel = ViewModelProvider(this, viewModelFactory).get(HoneyFrameBalancingViewModel::class.java)

        binding.honeyFrameViewModel = honeyFrameVewModel
        binding.setLifecycleOwner(this)

        val adapter = HoneyFrameBalancingAdapter(HoneyFrameBalancingClickListener { beehiveId ->
            //Toast.makeText(context,"${beehiveId}",Toast.LENGTH_SHORT).show()
            honeyFrameVewModel.onClickBeehive(beehiveId)
        })

        binding.honeyFrameList.adapter = adapter

        honeyFrameVewModel.beehives.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        honeyFrameVewModel.navigateToBeeReviewFragment.observe(this, Observer { beehives ->
            beehives?.let {
                findNavController().navigate(HoneyFrameBalancingFragmentDirections.actionHoneyframeBalancingToBeehiveReviewFragment(beehives,arguments.groupkey,3))
                honeyFrameVewModel.donenavigateToBeeReviewFragment()
            }
        })

        honeyFrameVewModel.navigateToHoneyFrameBalancingDescriptionFragment.observe(this, Observer {
            if(it==true){
                this.findNavController().navigate(HoneyFrameBalancingFragmentDirections.actionHoneyframeBalancingToHoneyframeBalancingDescription())
                honeyFrameVewModel.doneNavigateToHoneyFrameBalancingDescriptionFragment()
            }
        })

        honeyFrameVewModel.navigateToBeeManagementFragment.observe(this, Observer {
            if (it==true){
                this.findNavController().navigate(HoneyFrameBalancingFragmentDirections.actionHoneyframeBalancingToBeeManagementFragment(arguments.groupkey))
                honeyFrameVewModel.doneNavigateToBeeManagementFragment()
            }
        })


        val manager = GridLayoutManager(activity, 1, GridLayoutManager.VERTICAL,false)
        binding.honeyFrameList.layoutManager = manager

        return binding.root
    }
}