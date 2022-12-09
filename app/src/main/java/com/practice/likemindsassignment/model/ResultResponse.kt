package com.practice.likemindsassignment.model

data class ResultResponse(
    val definitions: List<Definition>,
    val pronunciation: Any,
    val word: String
)