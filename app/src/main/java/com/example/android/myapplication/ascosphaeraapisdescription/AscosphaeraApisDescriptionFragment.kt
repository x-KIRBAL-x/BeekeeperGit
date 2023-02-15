package com.example.android.myapplication.ascosphaeraapisdescription

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.android.myapplication.R
import com.example.android.myapplication.databinding.FragmentAscosphaeraApisInfoBinding

class AscosphaeraApisDescriptionFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentAscosphaeraApisInfoBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_ascosphaera_apis_info, container, false)
        return binding.root
    }
}