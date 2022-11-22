package com.example.cookingapp.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.cookingapp.HomePageActivity
import com.example.cookingapp.R

class AnimationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_animation, container, false)
        Handler(Looper.getMainLooper()!!).postDelayed({
            findNavController().navigate(R.id.action_animationFragment_to_homeFragment)
        },4000)

        return view
    }


}