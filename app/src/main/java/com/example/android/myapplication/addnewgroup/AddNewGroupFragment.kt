package com.example.android.myapplication.addnewgroup

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
import com.example.android.myapplication.R
import com.example.android.myapplication.database.BeeDatabase
import com.example.android.myapplication.databinding.FragmentAddNewGroupBinding

class AddNewGroupFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentAddNewGroupBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_add_new_group, container, false)

        val application = requireNotNull(this.activity).application

        val arguments = AddNewGroupFragmentArgs.fromBundle(requireArguments())

        val dataSource = BeeDatabase.getInstance(application).beeDatabaseDao
        val viewModelFactory = AddNewGroupViewModelFactory(dataSource,arguments.groupKey)

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        binding.setLifecycleOwner(this)

        val addNewGroupViewModel = ViewModelProvider(this, viewModelFactory).get(AddNewGroupViewModel::class.java)

        binding.addNewGroupViewModel = addNewGroupViewModel

        addNewGroupViewModel.clickDoneButton.observe(this, Observer {
            if (it==true) {
                        if( binding.newGroupName.text.toString() != "" && binding.newGroupLocation.text.toString() != ""){
                        addNewGroupViewModel.setvalue(binding.newGroupName.text.toString(),binding.newGroupLocation.text.toString())
                       // binding.newGroupName.text = null
                      //  binding.newGroupLocation.text = null
                            this.findNavController()
                            .navigate(AddNewGroupFragmentDirections.actionAddNewGroupFragmentToBeeGroupsFragment())
                            addNewGroupViewModel.doneNavigatingToGroupsFragment()
                    }
                    else{
                        Toast.makeText(application,"Please fill in all fields!",Toast.LENGTH_SHORT).show()
                    }

            }
        })

        return binding.root
    }
}