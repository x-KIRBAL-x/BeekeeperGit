package com.example.android.myapplication.addnewbeehive

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.myapplication.R
import com.example.android.myapplication.database.BeeDatabase
import com.example.android.myapplication.databinding.FragmentAddNewBeehiveBinding
import java.text.SimpleDateFormat
import java.util.*

class AddNewBeehiveFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentAddNewBeehiveBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_add_new_beehive, container, false)

        val application = requireNotNull(this.activity).application
        val arguments = AddNewBeehiveFragmentArgs.fromBundle(requireArguments())

        val dataSource = BeeDatabase.getInstance(application).beeDatabaseDao
        val viewModelFactory = AddNewBeehiveViewModelFactory(arguments.beeGroupKey,arguments.beehiveKey,arguments.navi,dataSource)

        val addNewBeehiveViewModel = ViewModelProvider(this, viewModelFactory).get(AddNewBeehiveViewModel::class.java)

        binding.addNewBeehiveViewModel = addNewBeehiveViewModel

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        binding.setLifecycleOwner(this)

        addNewBeehiveViewModel.clickDoneButton.observe(this, Observer {
            if(it!=null){
                if(binding.newBeehiveName.text.toString() != "" && binding.queenbeeAge.text.toString() != ""){
                    val queenYear: Int = binding.queenbeeAge.text.toString().toInt()
                    if(queenYear>(SimpleDateFormat("yyyy").format(Date()).toString().toInt()-6)
                        && queenYear<=SimpleDateFormat("yyyy").format(Date()).toString().toInt()){
                        if (it==1) {
                            addNewBeehiveViewModel.setValue(binding.newBeehiveName.text.toString(),queenYear)
                    this.findNavController().navigate(AddNewBeehiveFragmentDirections.actionAddNewBeehiveFragmentToBeehivesFragment(arguments.beeGroupKey))
                    addNewBeehiveViewModel.donenavigating()
                        }
                        if(it==2){
                            Toast.makeText(context,"${arguments.beehiveKey} ${arguments.beeGroupKey}",Toast.LENGTH_SHORT).show()
                            addNewBeehiveViewModel.updateBeehive(binding.newBeehiveName.text.toString(),queenYear)
                            this.findNavController().navigate(AddNewBeehiveFragmentDirections.actionAddNewBeehiveFragmentToBeehiveDetailFragment(arguments.beehiveKey,arguments.beeGroupKey))
                            addNewBeehiveViewModel.donenavigating()
                        }
                    }
                    else{
                        Toast.makeText(application, resources.getString(R.string.queenbee_warning),Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    Toast.makeText(application, "Please fill in all fields!",Toast.LENGTH_SHORT).show()
                }
            }
        })

        binding.newBeehiveName.doOnTextChanged { text, start, before, count ->
            if (text!!.length > 6){
                binding.beenamelayout.error = "No More!"
            }
            else if (text.length <=6){
                binding.beenamelayout.error = null
            }
        }

        binding.queenbeeAge.doOnTextChanged { text, start, before, count ->
            if (text!!.length > 4){
                binding.beeyearlayout.error = "No More!"
            }
            else if (text.length <= 4){
                binding.beeyearlayout.error = null
            }
        }

        return binding.root
    }
}