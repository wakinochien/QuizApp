package com.agmoacademy.quizapp.model

import com.squareup.moshi.Json


data class Quiz(
    @Json(name = "response_code")
    val responseCode: Long,
    val results: List<Question>,
)

data class Question(
    val category: Category,
    val type: Type,
    val difficulty: Difficulty,
    val question: String,

    @Json(name = "correct_answer")
    val correctAnswer: String,

    @Json(name = "incorrect_answers")
    val incorrectAnswers: List<String>,

    var isCorrect: Boolean = false,
    var isAnswered: Boolean = false,
)

enum class Category(val value: String, val id: Int) {
    @Json(name = "Science: Computers")
    ScienceComputers("Science: Computers", 18);
}

enum class Difficulty(val value: String) {
    @Json(name = "easy")
    Easy("easy")
}

enum class Type(val value: String) {
    @Json(name = "multiple")
    Multiple("multiple");
}
