package com.agmoacademy.quizapp

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.agmoacademy.quizapp.model.Question
import com.agmoacademy.quizapp.viewmodel.ApiStatus
import com.agmoacademy.quizapp.viewmodel.QuizViewModel

class MainActivity : AppCompatActivity(), OnClickListener {

    private val viewModel: QuizViewModel by viewModels()

    lateinit var recyclerView: RecyclerView
    lateinit var loadingBarContainer: FrameLayout
    lateinit var submitButton: Button

    lateinit var adapter: QuizAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        loadingBarContainer = findViewById(R.id.progress_container)

        submitButton = findViewById(R.id.submit_button)
        submitButton.setOnClickListener(this)
        adapter = QuizAdapter(this, mutableListOf())
        recyclerView.adapter = adapter

        // Use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true)
        initObserver()
        getData()
    }

    private fun getData() {
        viewModel.getQuizList()
    }

    private fun initObserver() {
        viewModel.quizList.observe(this) { list ->
            // Initialize data.
            val myDataset: List<Question> = list

            adapter.clear()
            adapter.addAll(myDataset)
        }

        viewModel.status.observe(this) { status ->
            when (status) {
                ApiStatus.LOADING -> {
                    loadingBarContainer.isVisible = true
                    submitButton.isVisible = false
                    recyclerView.isVisible = false
                }

                ApiStatus.ERROR -> {
                    loadingBarContainer.isVisible = false
                    submitButton.isVisible = true
                    recyclerView.isVisible = true
                    Toast.makeText(this, "Succes!", Toast.LENGTH_SHORT).show()
                }

                ApiStatus.DONE -> {
                    loadingBarContainer.isVisible = false
                    submitButton.isVisible = true
                    recyclerView.isVisible = true
                    Toast.makeText(this, "Error! Please Try again later!", Toast.LENGTH_SHORT)
                        .show()
                }

                else -> Unit
            }
        }
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

    override fun onClick(p0: View?) {
        val isAnsweredCount: Int = adapter.isCorrectCount()

        AlertDialog.Builder(this)
            .setTitle(getString(R.string.your_quiz_score))
            .setMessage(isAnsweredCount.toString())
            .show()
    }

}