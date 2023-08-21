package com.agmoacademy.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var radioButton1: RadioButton
    private lateinit var radioButton2: RadioButton
    private lateinit var radioButton3: RadioButton
    private lateinit var radioButton4: RadioButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.item_title)
        radioButton1 = findViewById(R.id.selection_1)
        radioButton2 = findViewById(R.id.selection_2)
        radioButton3 = findViewById(R.id.selection_3)
        radioButton4 = findViewById(R.id.selection_4)

        textView.text = "1. What does CPU stand For"
        radioButton1.text = "Computer Personal Unit"
        radioButton2.text = "Central Processing Unit"
        radioButton3.text = "Central Processor Unit"
        radioButton4.text = "Central Process Unit"

    }
}