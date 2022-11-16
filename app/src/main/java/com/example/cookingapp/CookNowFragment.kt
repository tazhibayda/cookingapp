package com.example.cookingapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.cookingapp.databinding.FragmentCookNowBinding
import com.example.cookingapp.models.CookNowData
import com.example.cookingapp.models.CookNowViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class CookNowFragment : Fragment() {
    private var _binding: FragmentCookNowBinding? = null
    private val binding: FragmentCookNowBinding get() = _binding!!
    private var counter: Int = 0
    private val viewModel: CookNowViewModel by viewModels()

    val listOfCookSteps :ArrayList<CookNowData> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCookNowBinding.inflate(inflater, container, false)

        listOfCookSteps.add(CookNowData("Step 1/10", "Chop pumpkin and sweet potato into small chunks, put them to the bowl.\n" +
                "Then add water and paprika. Select program no. 3 and cook for 20 mins."))
        listOfCookSteps.add(CookNowData("Step 2/10", "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa."))
        listOfCookSteps.add(CookNowData("Step 3/10","Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu."))
        listOfCookSteps.add(CookNowData("Step 4/10", "In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium."))
        listOfCookSteps.add(CookNowData("Step 5/10", "Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim."))
        listOfCookSteps.add(CookNowData("Step 6/10", "Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. "))
        listOfCookSteps.add(CookNowData("Step 7/10", "Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui. Etiam rhoncus. "))
        listOfCookSteps.add(CookNowData("Step 8/10", "Maecenas tempus, tellus eget condimentum rhoncus, sem quam semper libero, sit amet adipiscing sem neque sed ipsum."))
        listOfCookSteps.add(CookNowData("Step 9/10", "Nam quam nunc, blandit vel, luctus pulvinar, hendrerit id, lorem. Maecenas nec odio et ante tincidunt tempus. "))
        listOfCookSteps.add(CookNowData("Step 10/10", "Donec vitae sapien ut libero venenatis faucibus. Nullam quis ante. Etiam sit amet orci eget eros faucibus tincidunt. Duis leo."))

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttonDecr.setOnClickListener {
            viewModel.decreaseScore()
            if(counter<=9){
                counter--
                binding.cookingStepText.text = listOfCookSteps.get(counter).steps
                binding.cookingText1.text = listOfCookSteps.get(counter).stepDiscription
            }
        }
        binding.buttonIncr.setOnClickListener {
            viewModel.increaseScore()
            if(counter<=9){
                counter++
                binding.cookingStepText.text = listOfCookSteps.get(counter).steps
                binding.cookingText1.text = listOfCookSteps.get(counter).stepDiscription
            }
            else{
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Congratulations!")
                    .setMessage("Enjoy your meal! Bon appetit!")
                    .setCancelable(false)
                    .show()
            }
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
        var textToTV = newScore/10
        binding.progressBar.progress = newScore
        binding.textViewProgress.text = textToTV.toString()


//        if(counter<=9){
//            binding.cookingStepText.text = listOfCookSteps.get(counter).steps
//            binding.cookingText1.text = listOfCookSteps.get(counter).stepDiscription
//            counter++
//        }
    }


}
