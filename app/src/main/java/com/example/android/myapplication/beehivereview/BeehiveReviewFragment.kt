package com.example.android.myapplication.beehivereview

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
import com.example.android.myapplication.databinding.FragmentBeehiveReviewBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.log

class BeehiveReviewFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
     val binding: FragmentBeehiveReviewBinding = DataBindingUtil.inflate(
         inflater, R.layout.fragment_beehive_review, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = BeeDatabase.getInstance(application).beeDatabaseDao
        val arguments = BeehiveReviewFragmentArgs.fromBundle(requireArguments())
        val viewModelFactory = BeehiveReviewViewModelFactory(arguments.beehivekey,arguments.beegroupKey,arguments.navi,dataSource)
        val beehiveReviewViewModel = ViewModelProvider(this,viewModelFactory).get(BeehiveReviewViewModel::class.java)

        binding.beehiveReviewViewModel = beehiveReviewViewModel

        binding.setLifecycleOwner(this)

        beehiveReviewViewModel.navigateToDetailFragment.observe(this, Observer {
            if (it!=null){
                if(binding.broodframeNumberEdit.hint.toString() != "Edit" && binding.honeyFrameNumberEdit.hint.toString() != "Edit"
                    || binding.broodframeNumberEdit.text.toString() != "" && binding.honeyFrameNumberEdit.text.toString() != ""){
                    var bfn: Int
                    var hfn: Int
                    var queenbeeYear: Int
                    var noszema: Int = 0
                    var meszes: Int = 0
                    if (binding.nosemaSwitch.isChecked)
                        noszema=1
                    if (binding.meszesSwitch.isChecked)
                        meszes = 1
                    if(binding.broodframeNumberEdit.text.toString() != ""){
                        bfn = binding.broodframeNumberEdit.text.toString().toInt()
                    }
                    else{
                       bfn = binding.broodframeNumberEdit.hint.toString().toInt()
                    }
                    if(binding.honeyFrameNumberEdit.text.toString() != ""){
                        hfn = binding.honeyFrameNumberEdit.text.toString().toInt()
                    }
                    else {
                        hfn = binding.honeyFrameNumberEdit.hint.toString().toInt()
                    }
                    if(binding.queenbeeYearEdit.text.toString() != ""){
                        queenbeeYear = binding.queenbeeYearEdit.text.toString().toInt()
                    }
                    else{
                        queenbeeYear = binding.queenbeeYearEdit.hint.toString().toInt()
                    }
                    if(queenbeeYear>(SimpleDateFormat("yyyy").format(Date()).toString().toInt()-6)
                        && queenbeeYear<=SimpleDateFormat("yyyy").format(Date()).toString().toInt()) {
                            beehiveReviewViewModel.doneReview(
                                SimpleDateFormat("yyyy-MM-DD").format(
                                    Date()
                                ).toString(), bfn, hfn, noszema, meszes, queenbeeYear
                            )
                        if(it==0){
                            this.findNavController().navigate(
                                BeehiveReviewFragmentDirections.actionBeehiveReviewFragmentToBeehiveDetailFragment(
                                    arguments.beehivekey,
                                    arguments.beegroupKey
                                )
                            )}

                        if(it==1){
                            this.findNavController().navigate(BeehiveReviewFragmentDirections.actionBeehiveReviewFragmentToQueenbeeManagement(
                                arguments.beegroupKey))
                        }
                        if(it==2){
                            this.findNavController().navigate(BeehiveReviewFragmentDirections.actionBeehiveReviewFragmentToBroodframeBalancing(
                                arguments.beegroupKey))
                        }
                            beehiveReviewViewModel.doneNavigating()
                        }
                    else{
                        Toast.makeText(application, "The Queenbee year isn't correct!", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(application, "Please fill all field!",Toast.LENGTH_SHORT).show()
                }
            }
        })

        beehiveReviewViewModel.editBeequeenCondition.observe(this, Observer {
            if(it==true){
                var popupmenu: PopupMenu = PopupMenu(application,binding.queenbeeConditionEdit)
                popupmenu.menuInflater.inflate(R.menu.popup_menu_button_quality,popupmenu.menu)
                popupmenu.show()
                popupmenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                    when(item.itemId) {
                        R.id.popUpMenuQuality_One -> beehiveReviewViewModel.setBeequeenCondition(1)
                        R.id.popUpMenuQuality_Two -> beehiveReviewViewModel.setBeequeenCondition(2)
                        R.id.popUpMenuQuality_Three -> beehiveReviewViewModel.setBeequeenCondition(3)
                        R.id.popUpMenuQuality_Four -> beehiveReviewViewModel.setBeequeenCondition(4)
                        R.id.popUpMenuQuality_Five -> beehiveReviewViewModel.setBeequeenCondition(5)
                    }
                    true
                })
                beehiveReviewViewModel.doneEditBeequennCondition()
            }
        })

       beehiveReviewViewModel.editBeehivePopulation.observe(this, Observer {
            if(it==true){
                var popupmenu: PopupMenu = PopupMenu(application,binding.hivePopulationEdit)
                popupmenu.menuInflater.inflate(R.menu.popup_menu_button_quality,popupmenu.menu)
                popupmenu.show()
                popupmenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                    when(item.itemId) {
                        R.id.popUpMenuQuality_One -> beehiveReviewViewModel.setBeehivePopulation(1)
                        R.id.popUpMenuQuality_Two -> beehiveReviewViewModel.setBeehivePopulation(2)
                        R.id.popUpMenuQuality_Three -> beehiveReviewViewModel.setBeehivePopulation(3)
                        R.id.popUpMenuQuality_Four -> beehiveReviewViewModel.setBeehivePopulation(4)
                        R.id.popUpMenuQuality_Five -> beehiveReviewViewModel.setBeehivePopulation(5)
                    }
                    true
                })
                beehiveReviewViewModel.doneEditBeehivePopulation()
            }
        })

        beehiveReviewViewModel.editBroodframeQuantity.observe(this, Observer {
            if(it==true){
                var popupmenu: PopupMenu = PopupMenu(application,binding.broodframeEdit)
                popupmenu.menuInflater.inflate(R.menu.popup_menu_button_quantity,popupmenu.menu)
                popupmenu.show()
                popupmenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                    when(item.itemId) {
                        R.id.popUpMenuQuantity_One -> beehiveReviewViewModel.setBroodframeQuantity(1)
                        R.id.popUpMenuQuantity_Two -> beehiveReviewViewModel.setBroodframeQuantity(2)
                        R.id.popUpMenuQuantity_Three -> beehiveReviewViewModel.setBroodframeQuantity(3)
                    }
                    true
                })
                beehiveReviewViewModel.doneEditBroodframeQuantity()
            }
        })

        beehiveReviewViewModel.editHoneyframeQuantity.observe(this, Observer {
            if(it==true){
                var popupmenu: PopupMenu = PopupMenu(application,binding.honeyFrameEdit)
                popupmenu.menuInflater.inflate(R.menu.popup_menu_button_quantity,popupmenu.menu)
                popupmenu.show()
                popupmenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                    when(item.itemId) {
                        R.id.popUpMenuQuantity_One -> beehiveReviewViewModel.setHoneyframeQuantity(1)
                        R.id.popUpMenuQuantity_Two -> beehiveReviewViewModel.setHoneyframeQuantity(2)
                        R.id.popUpMenuQuantity_Three -> beehiveReviewViewModel.setHoneyframeQuantity(3)
                    }
                    true
                })
                beehiveReviewViewModel.doneEditHoneyframeQuantity()
            }
        })


        return binding.root
    }
}