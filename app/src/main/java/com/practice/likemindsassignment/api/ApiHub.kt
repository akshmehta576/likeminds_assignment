package com.practice.likemindsassignment.api

import com.practice.likemindsassignment.model.Definition
import com.practice.likemindsassignment.model.ResultResponse
import com.practice.likemindsassignment.utils.Constants
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiHub
{
    @Headers("Authorization: Token ${Constants.AUTH_TOKEN}")
    @GET("{word}")
    suspend fun getResult(
        @Path("word") word : String
    ) : Response<ResultResponse>
}