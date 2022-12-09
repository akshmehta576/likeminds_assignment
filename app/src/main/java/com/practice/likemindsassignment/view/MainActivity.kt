package com.practice.likemindsassignment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.practice.likemindsassignment.databinding.ActivityMainBinding
import com.practice.likemindsassignment.model.Definition
import com.practice.likemindsassignment.model.ResultResponse
import com.practice.likemindsassignment.viewmodel.ResultViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ResultViewModel
    private lateinit var resultAdapter: ResultAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ResultViewModel::class.java]


        binding.goButton.setOnClickListener {
            if (binding.wordEditext.text.isNotEmpty()) {
                val word = binding.wordEditext.text.toString().trim()
                viewModel.getResult(word)
                listofResult()
            } else {
                Toast.makeText(
                    applicationContext,
                    "Empty.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }

    private fun listofResult() {

        lifecycleScope.launchWhenStarted {
            viewModel.listofState.collect {
                when (it) {
                    is ResultViewModel.ListState.Success -> {
                        prepareRecyclerView(it.data.definitions as ArrayList<Definition>)
                    }
                    is ResultViewModel.ListState.Error ->
                    {

                    }
                    else ->
                    {

                    }
                }
            }
        }
    }

    private fun prepareRecyclerView(data: ArrayList<Definition>) {
        resultAdapter = ResultAdapter(data)
        binding.resultRecyclerview.apply {
            adapter = resultAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }
}
