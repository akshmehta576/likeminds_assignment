package com.practice.likemindsassignment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.practice.likemindsassignment.api.ApiHub
import com.practice.likemindsassignment.di.AppModule
import com.practice.likemindsassignment.model.Definition
import com.practice.likemindsassignment.model.ResultResponse
import retrofit2.Response

class ResultViewModel  : ViewModel() {
    private var resultLiveData = MutableLiveData<List<Definition>>()

    fun getResult(word: String) {

        AppModule.api.getResult(word).enqueue(object : retrofit2.Callback<ResultResponse> {

            override fun onResponse(
                call: retrofit2.Call<ResultResponse>,
                response: Response<ResultResponse>
            ) {
                if (response.body() != null) {
                    resultLiveData.value = response.body()!!.definitions
                } else {
                    return
                }
            }

            override fun onFailure(call: retrofit2.Call<ResultResponse>, t: Throwable) {

                Log.d("error is there", t.message.toString())
            }
        })


    }

    fun observeMovieLiveData(): LiveData<List<Definition>> {
        return resultLiveData
    }
}