package com.example.android.myapplication.sickbees

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
import com.example.android.myapplication.databinding.FragmentSickBeesBinding

class SickBeesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentSickBeesBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_sick_bees, container, false)

        val application = requireNotNull(this.activity).application
        val arguments = SickBeesFragmentArgs.fromBundle(requireArguments())
        val dataSource = BeeDatabase.getInstance(application).beeDatabaseDao
        val viewModelFactory = SickBeesViewModelFactory(arguments.groupkey,dataSource)
        val sickBeesviewModel = ViewModelProvider(this, viewModelFactory).get(SickBeesViewModel::class.java)

        binding.sickBeesViewModel = sickBeesviewModel

        binding.setLifecycleOwner(this)

        val adapter = SickBeesAdapter(SickBeesClickListener { beehiveID ->
            sickBeesviewModel.onClickBeehive(beehiveID)
        })

        binding.sickBeesList.adapter = adapter

        sickBeesviewModel.beehives.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        sickBeesviewModel.navigateToBeeReviewFragment.observe(this, Observer { beehive ->
            beehive?.let {
                findNavController().navigate(SickBeesFragmentDirections.actionSickBeesToBeehiveReviewFragment(beehive,arguments.groupkey,4))
                sickBeesviewModel.donenavigateToBeeReviewFragment()
            }
        })

        sickBeesviewModel.navigateToBeeManagementFragment.observe(this, Observer {
            if (it==true){
                this.findNavController().navigate(SickBeesFragmentDirections.actionSickBeesToBeeManagementFragment(arguments.groupkey))
                sickBeesviewModel.doneNavigateToBeeManagementFragment()
            }
        })


        val manager = GridLayoutManager(activity, 1, GridLayoutManager.VERTICAL,false)
        binding.sickBeesList.layoutManager = manager

        return binding.root
    }
}