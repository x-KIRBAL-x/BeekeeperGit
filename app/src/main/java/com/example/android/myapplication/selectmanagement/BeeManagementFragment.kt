package com.example.android.myapplication.selectmanagement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import com.example.android.myapplication.R
import com.example.android.myapplication.database.BeeDatabase
import com.example.android.myapplication.database.BeeDatabaseDao
import com.example.android.myapplication.databinding.FragmentSelectManagementBinding

class BeeManagementFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val binding: FragmentSelectManagementBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_select_management,container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = BeeDatabase.getInstance(application).beeDatabaseDao
        val arguments = BeeManagementFragmentArgs.fromBundle(requireArguments())
        val viewModelFactory = BeeManagementViewModelFactory(arguments.groupkey,dataSource)

        val beeManagementViewModel = ViewModelProvider(this,viewModelFactory).get(BeeManagementViewModel::class.java)

        binding.beeManagementViewModel = beeManagementViewModel
        binding.setLifecycleOwner(this)

        beeManagementViewModel.navigateToBroodFrameBalancingFragment.observe(this, Observer {
            if(it==true){
                this.findNavController().navigate(BeeManagementFragmentDirections.actionBeeManagementFragmentToBroodframeBalancing(arguments.groupkey))
                beeManagementViewModel.donenavigateToBroodFrameBalancingFragment()
            }
        })

        beeManagementViewModel.navigateToQueenBeeManagementFragment.observe(this, Observer {
            if(it==true){
                this.findNavController().navigate(BeeManagementFragmentDirections.actionBeeManagementFragmentToQueenbeeManagement(arguments.groupkey))
                beeManagementViewModel.doneNavigatingToQueenBeeMAnagementFragment()
            }
        })

        beeManagementViewModel.navigateToSelectGroupFragment.observe(this, Observer {
            if(it==true){
                this.findNavController().navigate(BeeManagementFragmentDirections.actionBeeManagementFragmentToSelectGroup())
                beeManagementViewModel.doneNavigateToSelectGroupFragment()
            }
        })

        return binding.root
    }
}