package com.example.quizapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FlashcardActivity : AppCompatActivity() {

    // Declare and initialize the arrays for questions and answers.
    private val questions = arrayOf(
        "Obama was the president of South Africa.",
        "Nelson Mandela was born in USA.",
        "The Roman Empire fell in 476 AD.",
        "The United States declared independence in 1776.",
        "World War I began in 2025."
    )
    private val answers = booleanArrayOf(false, false, true, true, false)

    // Initialize the score counter.
    private var score = 0

    // Initialize the question index.
    private var currentQuestionIndex = 0

    // Declare UI elements.  These are declared as nullable, and initialized in onCreate
    private lateinit var questionText: TextView
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var feedbackText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flashcard)

        // Initialize UI elements using findViewById.
        questionText = findViewById(R.id.questionText)
        trueButton = findViewById(R.id.trueButton)
        falseButton = findViewById(R.id.falseButton)
        nextButton = findViewById(R.id.nextButton)
        feedbackText = findViewById(R.id.feedbackText)

        // Disable the 'Next' button initially.  It should only be enabled after an answer.
        nextButton.isEnabled = false

        // Load and display the first question.
        loadQuestion()

        // Set click listeners for the answer buttons.
        trueButton.setOnClickListener {
            checkAnswer(true)
        }
        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        // Set a click listener for the next button.
        nextButton.setOnClickListener {
            // Move to the next question.
            currentQuestionIndex++
            // Check if all questions have been answered.
            if (currentQuestionIndex < questions.size) {
                // Load the next question.
                loadQuestion()
            } else {
                // If all questions are done, move to the score screen.
                val intent = Intent(this, ScoreActivity::class.java)
                intent.putExtra("score", score) // Pass the score to the ScoreActivity.
                startActivity(intent)
                finish() //prevent back button
            }
        }
    }

    /**
     * Loads the current question and updates the UI.
     */
    private fun loadQuestion() {
        // Set the question text.
        questionText.text = questions[currentQuestionIndex]
        // Clear any previous feedback.
        feedbackText.text = ""
        // Re-enable the answer buttons.
        trueButton.isEnabled = true
        falseButton.isEnabled = true
        // Disable the next button until an answer is given.
        nextButton.isEnabled = false
    }

    /**
     * Checks the user's answer and updates the score.
     * @param userAnswer The user's answer (true or false).
     */
    @SuppressLint("SetTextI18n")
    private fun checkAnswer(userAnswer: Boolean) {
        // Find the right answer to the current question.
        val correctAnswer = answers[currentQuestionIndex]
        // Compare the user's answer with the correct answer.
        if (userAnswer == correctAnswer) {
            // When the answer is accurate, modify the score and present 'Correct!'.
            score++
            feedbackText.text = "Correct!"
        } else {
            // If the answer is incorrect, display "Incorrect!".
            feedbackText.text = "Incorrect!"
        }

        // Disable the answer buttons after the user has answered.
        trueButton.isEnabled = false
        falseButton.isEnabled = false
        // Enable the next button after the user has answered.
        nextButton.isEnabled = true
    }
}

