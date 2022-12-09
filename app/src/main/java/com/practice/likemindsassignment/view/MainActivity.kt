package com.practice.likemindsassignment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.practice.likemindsassignment.databinding.ActivityMainBinding
import com.practice.likemindsassignment.viewmodel.ResultViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: ResultViewModel
    private lateinit var movieAdapter : ResultAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prepareRecyclerView()
        viewModel = ViewModelProvider(this)[ResultViewModel::class.java]

        binding.goButton.setOnClickListener {
            if (binding.wordEditext.text.isNotEmpty()) {
                var word = binding.wordEditext.text.toString()
                viewModel.getResult(word)

                viewModel.observeMovieLiveData().observe(this, Observer { resultList ->
                    movieAdapter.setList(resultList)
                })
            }
            else
            {
                Toast.makeText(applicationContext, "Enter word please.", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun prepareRecyclerView() {
        movieAdapter = ResultAdapter()
        binding.resultRecyclerview.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = movieAdapter
        }
    }
}
