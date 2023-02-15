package com.example.android.myapplication.selectgroup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android.myapplication.R
import com.example.android.myapplication.database.BeeDatabase
import com.example.android.myapplication.database.BeeDatabaseDao
import com.example.android.myapplication.databinding.FragmentSelectGroupBinding

class SelectGroupFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentSelectGroupBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_select_group, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = BeeDatabase.getInstance(application).beeDatabaseDao
        val viewModelFactory = SelectGroupViewModelFactory(dataSource)

        val selectGroupsViewModel = ViewModelProvider(this, viewModelFactory).get(SelectGroupViewModel::class.java)

        binding.selectGroupViewModel = selectGroupsViewModel

        binding.setLifecycleOwner(this)

        val adapter = SelectGroupAdapter(SelectGroupListener { groupId ->
            selectGroupsViewModel.onBeeGroupsClicked(groupId)
        })
        binding.selectGroupsList.adapter = adapter

        selectGroupsViewModel.groups.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        selectGroupsViewModel.navigateToSelectManagementFragment.observe(this, Observer { group ->
            group?.let{
                this.findNavController().navigate(SelectGroupFragmentDirections.actionSelectGroupToBeeManagementFragment(group))
                selectGroupsViewModel.doneNavigatingToSelectManagementFragment()
            }
        })

        selectGroupsViewModel.navigateToBeeMenuFragment.observe(this, Observer{
            if (it==true){
                this.findNavController().navigate(SelectGroupFragmentDirections.actionSelectGroupToBeeKeeperMenuFragment())
                selectGroupsViewModel.doneNavigatingMenuFragment()
            }
        })

        val manager = GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL,false)
        binding.selectGroupsList.layoutManager = manager

        return binding.root
    }
}