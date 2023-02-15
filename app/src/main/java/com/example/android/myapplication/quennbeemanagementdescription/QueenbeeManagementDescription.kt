package com.example.android.myapplication.quennbeemanagementdescription

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.android.myapplication.R
import com.example.android.myapplication.databinding.FragmentQueenbeeManagementInfoBinding

class QueenbeeManagementDescription: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentQueenbeeManagementInfoBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_queenbee_management_info, container, false)
        return binding.root
    }
}