package com.example.android.myapplication.beekeppermenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.myapplication.R
import com.example.android.myapplication.database.BeeDatabase
import com.example.android.myapplication.databinding.FragmentBeekeeperMenuBinding

class BeeKeeperMenuFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentBeekeeperMenuBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_beekeeper_menu,container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = BeeDatabase.getInstance(application).beeDatabaseDao
        val viewModelFactory = BeeKeeperMenuViewModelFactory()

        val beeKeeperViewModel =
            ViewModelProvider(this, viewModelFactory).get(BeeKeeperMenuViewModel::class.java)

        binding.beeKeeperMenuViewModel = beeKeeperViewModel

        binding.setLifecycleOwner(this)

        beeKeeperViewModel.navigateToBeeGroupsFragment.observe(this, Observer {
            if (it==true){
                this.findNavController().navigate(BeeKeeperMenuFragmentDirections.actionBeeKeeperMenuFragmentToBeeGroupsFragment())
                beeKeeperViewModel.doneNavigating()
            }
        })

        beeKeeperViewModel.navigateToSelectGroupFragment.observe(this, Observer {
            if(it==true){
                this.findNavController().navigate(BeeKeeperMenuFragmentDirections.actionBeeKeeperMenuFragmentToSelectGroup())
                beeKeeperViewModel.clickmanagementbuttondone()
            }
        })

        return binding.root
    }
}