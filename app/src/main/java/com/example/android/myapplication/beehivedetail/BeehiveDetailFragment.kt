package com.example.android.myapplication.beehivedetail

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
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import com.example.android.myapplication.R
import com.example.android.myapplication.beehives.BeehivesFragmentArgs
import com.example.android.myapplication.beehives.BeehivesViewModel
import com.example.android.myapplication.beehives.BeehivesViewModelFactory
import com.example.android.myapplication.database.BeeDatabase
import com.example.android.myapplication.databinding.FragmentBeehiveDetailBinding

class BeehiveDetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentBeehiveDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_beehive_detail, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = BeeDatabase.getInstance(application).beeDatabaseDao
        val arguments = BeehiveDetailFragmentArgs.fromBundle(requireArguments())
        val viewModelFactory = BeehiveDetailViewModelFactory(arguments.beehivekey,arguments.beeGroupKey,dataSource)

        val beehiveDetailViewModel = ViewModelProvider(this,viewModelFactory).get(BeehiveDetailViewModel::class.java)

        binding.beehiveDetailViewModel = beehiveDetailViewModel


        binding.setLifecycleOwner(this)

        beehiveDetailViewModel.navigateToBeehivesFragment.observe(this, Observer {
            if (it==true){
                this.findNavController().navigate(BeehiveDetailFragmentDirections.actionBeehiveDetailFragmentToBeehivesFragment(arguments.beeGroupKey))
                beehiveDetailViewModel.doneNavigateToBeehivesFragment()
            }
        })

        beehiveDetailViewModel.navgateToBeehiveDeleteFragment.observe(this, Observer {
            if (it==true){
                this.findNavController().navigate(BeehiveDetailFragmentDirections.actionBeehiveDetailFragmentToBeehiveDeleteFragment(arguments.beehivekey,arguments.beeGroupKey))
                beehiveDetailViewModel.doneNavgateToBeehiveDeleteFragment()
            }
        })

        beehiveDetailViewModel.navigateToBeehiveReviewFragment.observe(this, Observer {
            if (it==true){
                this.findNavController().navigate(BeehiveDetailFragmentDirections.actionBeehiveDetailFragmentToBeehiveReviewFragment(arguments.beehivekey, arguments.beeGroupKey,0))
                beehiveDetailViewModel.doneNavigateToBeehiveReviewFragment()
            }
        })

        beehiveDetailViewModel.navigateToAddNewBeehiveFragment.observe(this, Observer {
            if (it!=null){
                Toast.makeText(context,"${arguments.beehivekey} ${arguments.beeGroupKey}",Toast.LENGTH_SHORT).show()
                this.findNavController().navigate(BeehiveDetailFragmentDirections.actionBeehiveDetailFragmentToAddNewBeehiveFragment(arguments.beeGroupKey, arguments.beehivekey,2))
                beehiveDetailViewModel.doneNavigateToAddNewBeehiveFragment()
            }
        })
        return binding.root
    }
}