package com.example.android.myapplication.groupdelete

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.myapplication.R
import com.example.android.myapplication.database.BeeDatabase
import com.example.android.myapplication.databinding.FragmentGroupDeleteBinding

class GroupDeleteFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentGroupDeleteBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_group_delete,container,false)

        val application = requireNotNull(this.activity).application
        val arguments = GroupDeleteFragmentArgs.fromBundle(requireArguments())

        val dataSource = BeeDatabase.getInstance(application).beeDatabaseDao
        val viewModelFactory = GroupDeleteViewModelFactory(arguments.groupKey, dataSource)

        val groupDeleteViewModel = ViewModelProvider(this, viewModelFactory).get(GroupDeleteViewModel::class.java)

        binding.groupDeleteViewModel = groupDeleteViewModel
        binding.setLifecycleOwner(this)

        groupDeleteViewModel.navigateToBeeGroupsFragment.observe(this, Observer {
            if(it==true){
                this.findNavController().navigate(GroupDeleteFragmentDirections.actionGroupDeleteFragmentToBeeGroupsFragment())
                groupDeleteViewModel.doneNavigateToBeeGroupsFragment()
            }
        })

        groupDeleteViewModel.navigateToBeehivesFragment.observe(this, Observer {
            if(it==true){
                this.findNavController().navigate(GroupDeleteFragmentDirections.actionGroupDeleteFragmentToBeehivesFragment(arguments.groupKey))
                groupDeleteViewModel.doneNavigatingToBeehivesFragment()
            }
        })
        return binding.root
    }
}