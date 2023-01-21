package com.example.android.myapplication.beehivedelete

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
import com.example.android.myapplication.databinding.FragmentBeehiveDeleteBinding

class BeehiveDeleteFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentBeehiveDeleteBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_beehive_delete,container,false)

        val application = requireNotNull(this.activity).application
        val arguments = BeehiveDeleteFragmentArgs.fromBundle(requireArguments())

        val dataSource = BeeDatabase.getInstance(application).beeDatabaseDao
        val viewModelFactory = BeehiveDeleteViewModelFactory(arguments.beehivekey,arguments.beeGroupKey,dataSource)

        val beehiveDeleteViewModel = ViewModelProvider(this, viewModelFactory)
            .get(BeehiveDeleteViewModel::class.java)
        binding.beehiveDeleteViewModel = beehiveDeleteViewModel
        binding.setLifecycleOwner(this)

        beehiveDeleteViewModel.navigateToBeehiveDetailFragment.observe(this, Observer {
            if (it==true){
                this.findNavController().navigate(BeehiveDeleteFragmentDirections.actionBeehiveDeleteFragmentToBeehiveDetailFragment(arguments.beehivekey,arguments.beeGroupKey))
                beehiveDeleteViewModel.doneNavigateToBeehiveDetailFragment()
            }
        })

        beehiveDeleteViewModel._navigateToBeehivesFragment.observe(this, Observer {
            if (it==true){
                this.findNavController().navigate(BeehiveDeleteFragmentDirections.actionBeehiveDeleteFragmentToBeehivesFragment(arguments.beeGroupKey))
                beehiveDeleteViewModel.doneNavigateToBeehivesFragment()
            }
        })

        return binding.root
    }
}