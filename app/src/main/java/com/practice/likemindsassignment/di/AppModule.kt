package com.practice.likemindsassignment.di

import com.practice.likemindsassignment.api.ApiHub
import com.practice.likemindsassignment.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppModule {

    val api: ApiHub by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiHub::class.java)

    }

}