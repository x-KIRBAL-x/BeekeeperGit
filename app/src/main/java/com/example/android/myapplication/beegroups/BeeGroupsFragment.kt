package com.example.android.myapplication.beegroups

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android.myapplication.R
import com.example.android.myapplication.database.BeeDatabase
import com.example.android.myapplication.databinding.FragmentBeeGroupsBinding

class BeeGroupsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        val binding: FragmentBeeGroupsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_bee_groups, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = BeeDatabase.getInstance(application).beeDatabaseDao
        val viewModelFactory = BeeGroupsViewModelFactory(dataSource)

        val beeGroupsViewModel = ViewModelProvider(this, viewModelFactory).get(BeeGroupsViewModel::class.java)

        binding.beeGroupsViewModel = beeGroupsViewModel

        val adapter = BeeGroupsAdapter(BeeGroupListener { groupId ->
           // Toast.makeText(context, "${groupId}", Toast.LENGTH_SHORT).show()
            beeGroupsViewModel.onBeeGroupsClicked(groupId)
        })
        binding.beegroupsList.adapter = adapter


        beeGroupsViewModel.groups.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })


        binding.setLifecycleOwner(this)

        beeGroupsViewModel.navigateToBeeMenuFragment.observe(this, Observer {
            if(it==true){
                this.findNavController().navigate(BeeGroupsFragmentDirections.actionBeeGroupsFragmentToBeeKeeperMenuFragment())
                beeGroupsViewModel.doneNavigatingClose()
            }
        })

        beeGroupsViewModel.navigateToAddNewGroupFragment.observe(this, Observer {
            group -> group?.let{
                this.findNavController().navigate(BeeGroupsFragmentDirections.actionBeeGroupsFragmentToAddNewGroupFragment(group.groupId))
                beeGroupsViewModel.doneNavigatingAddNewGroup()
            }
        })

        beeGroupsViewModel.navigateToBeehivesfragment.observe(this, Observer { group ->
            group?.let {
                this.findNavController().navigate(BeeGroupsFragmentDirections.actionBeeGroupsFragmentToBeehivesFragment(group))
                beeGroupsViewModel.doneNavigatingToBeehiveFragment()
            }
        })

        val manager = GridLayoutManager(activity,3,GridLayoutManager.VERTICAL,false)
        binding.beegroupsList.layoutManager = manager



        return binding.root
    }
}