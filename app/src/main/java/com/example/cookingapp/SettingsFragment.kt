package com.example.cookingapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cookingapp.databinding.SettingsFragmentBinding

class SettingsFragment : Fragment() {
    private var _binding :SettingsFragmentBinding? = null
    private val binding :SettingsFragmentBinding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding =SettingsFragmentBinding.inflate(inflater)
        return binding.root
    }

}