package com.example.quizapp

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class ReviewActivity : AppCompatActivity() {

    // Declare the questions and answers arrays.  These are duplicated from QuizActivity
    // to keep ReviewActivity self-contained.  In a larger app, you might want to
    // define these in a separate data class or singleton.
    private val questions = arrayOf(
        "Obama was the president of South Africa.",
        "Nelson Mandela was born in USA.",
        "The Roman Empire fell in 476 AD.",
        "The United States declared independence in 1776.",
        "World War I began in 2025."
    )
    private val answers = booleanArrayOf(true, false, true, true, true)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        // Initialize UI elements.
        val reviewLayout: LinearLayout = findViewById(R.id.reviewLayout)

        // Loop through the questions and answers, and create TextViews for each.
        for (i in questions.indices) {
            // Create a TextView for the question.
            val questionTextView = TextView(this)
            questionTextView.text = questions[i]
            questionTextView.textSize = 18f
            questionTextView.setTextColor(ContextCompat.getColor(this, R.color.black)) // Set text color
            reviewLayout.addView(questionTextView)

            // Create a TextView for the answer.
            val answerTextView = TextView(this)
            val answerString = "Answer: " + if (answers[i]) "True" else "False"
            answerTextView.text = answerString
            answerTextView.textSize = 16f

            reviewLayout.addView(answerTextView)

            // Add some spacing between questions.
            val spaceTextView = TextView(this)
            spaceTextView.height = 16 // in pixels
            reviewLayout.addView(spaceTextView)
        }
    }
}
