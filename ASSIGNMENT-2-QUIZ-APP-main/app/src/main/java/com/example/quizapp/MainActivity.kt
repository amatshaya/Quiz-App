package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI elements
        val welcomeText: TextView = findViewById(R.id.welcomeText)
        val startButton: Button = findViewById(R.id.startButton)

        // Set a click listener for the start button to begin the quiz.
        startButton.setOnClickListener {
            // Create an intent to open the FlashcardsActivity.
            val intent = Intent(this, FlashcardActivity::class.java)
            // Start the new activity.
            startActivity(intent)
        }
    }
}
