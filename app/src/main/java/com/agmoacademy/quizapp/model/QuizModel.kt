package com.agmoacademy.quizapp.model

import com.squareup.moshi.Json


data class Quiz(
    @Json(name = "response_code")
    val responseCode: Long,
    val results: List<Question>,
)

data class Question(
    val category: String,
    val type: String,
    val difficulty: String,
    val question: String,

    @Json(name = "correct_answer")
    val correctAnswer: String,

    @Json(name = "incorrect_answers")
    val incorrectAnswers: List<String>,

    var isCorrect: Boolean = false,
)
