package com.agmoacademy.quizapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.agmoacademy.quizapp.model.Question

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView

    lateinit var adapter: QuizAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize data.
        val myDataset: List<Question> = loadQuiz()

        recyclerView = findViewById(R.id.recycler_view)
        adapter = QuizAdapter(myDataset)
        recyclerView.adapter = adapter

        // Use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true)

    }

    private fun loadQuiz(): List<Question> {
        val list = listOf<Question>(
            Question(
                category = "Science: Computers",
                type = "multiple",
                difficulty = "easy",
                question = "In any programming language, what is the most common way to iterate through an array?",
                correctAnswer = "&#039;For&#039; loops",
                incorrectAnswers = listOf(
                    "&#039;If&#039; Statements",
                    "&#039;Do-while&#039; loops",
                    "&#039;While&#039; loops"
                ),
            ),
            Question(
                category = "Science: Computers",
                type = "multiple",
                difficulty = "easy",
                question = "What does CPU stand for?",
                correctAnswer = "Central Processing Unit",
                incorrectAnswers = listOf(
                    "Central Process Unit",
                    "Computer Personal Unit",
                    "Central Processor Unit"
                ),
            )
        )

        return list
    }

}