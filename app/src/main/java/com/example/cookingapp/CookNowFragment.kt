package com.example.cookingapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.cookingapp.databinding.FragmentCookNowBinding
import com.example.cookingapp.models.CookNowViewModel


class CookNowFragment : Fragment() {
    private var _binding: FragmentCookNowBinding? = null
    private val binding: FragmentCookNowBinding get() = _binding!!

    private val viewModel: CookNowViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCookNowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttonDecr.setOnClickListener {
            viewModel.decreaseScore()
        }
        binding.buttonIncr.setOnClickListener {
            viewModel.increaseScore()
        }
        viewModel.score.observe(viewLifecycleOwner) { newScore ->
            updateScore(newScore)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateScore(newScore: Int) {
        binding.progressBar.progress = newScore
        binding.textViewProgress.text = newScore.toString()

    }


}
