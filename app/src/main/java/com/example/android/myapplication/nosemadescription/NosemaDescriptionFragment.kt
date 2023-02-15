package com.example.android.myapplication.nosemadescription

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.android.myapplication.R
import com.example.android.myapplication.databinding.FragmentNosemaInfoBinding

class NosemaDescriptionFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentNosemaInfoBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_nosema_info, container, false)
        return binding.root
    }
}