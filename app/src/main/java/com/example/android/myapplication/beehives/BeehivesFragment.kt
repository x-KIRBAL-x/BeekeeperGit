package com.example.android.myapplication.beehives

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
import com.example.android.myapplication.databinding.FragmentBeehivesBinding

class BeehivesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val binding: FragmentBeehivesBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_beehives, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = BeeDatabase.getInstance(application).beeDatabaseDao
        val arguments = BeehivesFragmentArgs.fromBundle(requireArguments())
        val viewModelFactory = BeehivesViewModelFactory(arguments.beeGroupKey, dataSource)

        val beeHivesViewModel = ViewModelProvider(this, viewModelFactory).get(BeehivesViewModel::class.java)

        binding.beehivesViewModel = beeHivesViewModel

        val adapter = BeehivesAdapter(BeehiveListener { beehivesId ->
           // Toast.makeText(context,"${beehivesId}",Toast.LENGTH_SHORT).show()
            beeHivesViewModel.onBeehiveClicked(beehivesId)
        })
        binding.beehivesList.adapter = adapter

        beeHivesViewModel.beehives.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.setLifecycleOwner(this)

        beeHivesViewModel.navigateToBeeGroupsFragment.observe(this,Observer{
            if (it==true){
                this.findNavController().navigate(BeehivesFragmentDirections.actionBeehivesFragmentToBeeGroupsFragment())
                beeHivesViewModel.doneNavigateToBeeGroupsFragment()
            }
        })

        beeHivesViewModel.navigateToAddNewBeehiveFragment.observe(this, Observer {
            beehive -> beehive?.let {
                this.findNavController().navigate(BeehivesFragmentDirections.actionBeehivesFragmentToAddNewBeehiveFragment(arguments.beeGroupKey,beehive.beehiveId,1))
            beeHivesViewModel.doneNavigateToAddNewBeehiveFragment()
        }
        })

        beeHivesViewModel.navigateToGroupDeleteFragment.observe(this, Observer {
            if (it==true){
                this.findNavController().navigate(BeehivesFragmentDirections.actionBeehivesFragmentToGroupDeleteFragment(arguments.beeGroupKey))
                beeHivesViewModel.doneNavigateToGroupDeleteFragment()
            }
        })

        beeHivesViewModel.navigateToBeeDetailFragment.observe(this, Observer { beehive ->
        beehive?.let {
            this.findNavController().navigate(BeehivesFragmentDirections.actionBeehivesFragmentToBeehiveDetailFragment(beehive,arguments.beeGroupKey))
            beeHivesViewModel.doneNavigateToBeeDetailFragment()
        }
        })

        beeHivesViewModel.navigateToAddNewGroupFragment.observe(this, Observer {
            if (it==true){
                this.findNavController().navigate(BeehivesFragmentDirections.actionBeehivesFragmentToAddNewGroupFragment(arguments.beeGroupKey, 2))
                beeHivesViewModel.doneNavigateToAddNewGroupFragment()
            }
        })

        val manager = GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL,false)
        binding.beehivesList.layoutManager = manager

        return binding.root
    }
}