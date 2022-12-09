package com.practice.likemindsassignment.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.practice.likemindsassignment.api.ApiHub
import com.practice.likemindsassignment.di.AppModule
import com.practice.likemindsassignment.model.Definition
import com.practice.likemindsassignment.model.ResultResponse
import com.practice.likemindsassignment.repository.ResultRepository
import com.practice.likemindsassignment.utils.Resources
import com.practice.likemindsassignment.view.MainActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(private val repository: ResultRepository)  : ViewModel() {

    private val _listofState = MutableStateFlow<ListState>(ListState.Empty)
    val listofState: StateFlow<ListState> = _listofState.asStateFlow()
    fun getResult(word: String) {

        viewModelScope.launch(Dispatchers.IO) {
            try {

                when (val response = repository.getResult(word)) {
                    is Resources.Success -> {
                        Log.i("datagethere", response.data.toString())
                        if (response.data != null) {
                            _listofState.value = ListState.Success(response.data)
                        }
                        else
                        {

                            _listofState.value = ListState.Error(response.message.toString())
                        }

                    }
                    is Resources.Error -> {

                        _listofState.value = ListState.Error(response.message.toString())
                    }

                    else -> {}
                }


            } catch (e: Exception) {
            }
        }


    }
    sealed class ListState {
        data class Success(
            val data: ResultResponse
        ) : ListState()

        data class Error(val message: String) : ListState()
        object Empty : ListState()
    }
}