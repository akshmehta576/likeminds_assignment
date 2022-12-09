package com.practice.likemindsassignment.repository

import com.practice.likemindsassignment.api.ApiHub
import com.practice.likemindsassignment.model.ResultResponse
import com.practice.likemindsassignment.utils.Resources
import javax.inject.Inject

class ResultRepository @Inject constructor(private val apiHub: ApiHub) {
    suspend fun getResult(word: String): Resources<ResultResponse?> {
        val response = try {
            apiHub.getResult(word)
        } catch (e: Exception) {
            return Resources.Error("Error: ${e.message}")
        }

        return Resources.Success(response.body())
    }
}