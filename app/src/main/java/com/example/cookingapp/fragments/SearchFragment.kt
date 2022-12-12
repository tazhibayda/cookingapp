package com.example.cookingapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.snapshots.Snapshot.Companion.observe
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cookingapp.HomePageActivity
import com.example.cookingapp.R
import com.example.cookingapp.adapters.RecipesAdapter
import com.example.cookingapp.databinding.FragmentSearchBinding
import com.example.cookingapp.viewModel.InspirationViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {
    private lateinit var binding : FragmentSearchBinding
    private lateinit var viewModel :InspirationViewModel
    private lateinit var searchRecyclerViewAdapter :RecipesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (activity as HomePageActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding  = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareRecyclerView()
        binding.arrowImg.setOnClickListener {searchMeals()  }

        observeSearchRecipesLiveData()

        var searchJob :Job? = null
        binding.edSearchBox.addTextChangedListener {searchQuery ->
            searchJob?.cancel()
            searchJob = lifecycleScope.launch {
                delay(200)
                viewModel.searchMeals(searchQuery.toString())
            }
        }
    }

    private fun observeSearchRecipesLiveData() {
        viewModel.observeSearchMealsLiveData().observe(viewLifecycleOwner,Observer{melsList->
            searchRecyclerViewAdapter.differ.submitList(melsList)

        })
    }

    private fun searchMeals() {
        val searchQuery = binding.edSearchBox.text.toString()
        if(searchQuery.isNotEmpty()){
            viewModel.searchMeals(searchQuery)
        }
    }

    private fun prepareRecyclerView() {
        searchRecyclerViewAdapter = RecipesAdapter()
        binding.rvSearchRecieps.apply {
            layoutManager =GridLayoutManager(context,2,GridLayoutManager.VERTICAL, false)
            adapter = searchRecyclerViewAdapter
        }
    }
}
