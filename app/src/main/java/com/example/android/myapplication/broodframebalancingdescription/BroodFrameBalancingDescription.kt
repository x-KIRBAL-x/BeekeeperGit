package com.example.android.myapplication.broodframebalancingdescription

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.android.myapplication.R
import com.example.android.myapplication.databinding.FragmentBroodFrameBalancingInfoBinding

class BroodFrameBalancingDescription: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentBroodFrameBalancingInfoBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_brood_frame_balancing_info, container, false)
        return binding.root
    }
}