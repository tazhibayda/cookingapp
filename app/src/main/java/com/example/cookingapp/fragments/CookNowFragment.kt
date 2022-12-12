package com.example.cookingapp.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.cookingapp.CookNowViewModel
import com.example.cookingapp.databinding.FragmentCookNowBinding
import com.example.cookingapp.models.CookStep


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
        binding.dishName.text = viewModel.dish.name
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttonDecr.setOnClickListener {
            viewModel.onClickDecrease()
        }
        binding.buttonIncr.setOnClickListener {
            viewModel.onClickIncrease()
        }
        /* binding.btnFeedBack.setOnClickListener{
             findNavController().navigate(R.id.action_cookNowFragment_to_cookNowQualityFragment2)
         }*/
        viewModel.currentStep.observe(viewLifecycleOwner) { currentStep ->
            updateCurrentStep(currentStep)
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("SetTextI18n")
    private fun updateCurrentStep(currentStep: CookStep) {
        val (currentStepNumber, _, stepsCount) = currentStep
        binding.cookingStepText.text = "Step: ${currentStep.stepNumber}/$stepsCount"
        binding.cookingText1.text = currentStep.description
        binding.progressBar.max = stepsCount
        binding.progressBar.progress = currentStepNumber
        binding.textViewProgress.text = currentStepNumber.toString()


    }


}