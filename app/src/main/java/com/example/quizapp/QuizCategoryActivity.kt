package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class QuizCategoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_category)

        val cardHistory = findViewById<CardView>(R.id.cardHistory)
        val cardGeography = findViewById<CardView>(R.id.cardGeography)
        val cardSpace = findViewById<CardView>(R.id.cardSpace)
        val cardSports = findViewById<CardView>(R.id.cardSports)
        val cardEntertainment = findViewById<CardView>(R.id.cardEntertainment)
        val cardMaths = findViewById<CardView>(R.id.cardMaths)

        cardHistory.setOnClickListener {
            val intent = Intent(this, QuizQuestionsActivity::class.java)
            intent.putExtra(Constants.QUIZ_CATEGORY, "History")
            startActivity(intent)
        }

        cardGeography.setOnClickListener {
            val intent = Intent(this, QuizQuestionsActivity::class.java)
            intent.putExtra(Constants.QUIZ_CATEGORY, "Geography")
            startActivity(intent)
        }

        cardSpace.setOnClickListener {
            val intent = Intent(this, QuizQuestionsActivity::class.java)
            intent.putExtra(Constants.QUIZ_CATEGORY, "Space")
            startActivity(intent)
        }

        cardSports.setOnClickListener {
            val intent = Intent(this, QuizQuestionsActivity::class.java)
            intent.putExtra(Constants.QUIZ_CATEGORY, "Sports")
            startActivity(intent)
        }

        cardEntertainment.setOnClickListener {
            val intent = Intent(this, QuizQuestionsActivity::class.java)
            intent.putExtra(Constants.QUIZ_CATEGORY, "Entertainment")
            startActivity(intent)
        }

        cardMaths.setOnClickListener {
            val intent = Intent(this, QuizQuestionsActivity::class.java)
            intent.putExtra(Constants.QUIZ_CATEGORY, "Reasoning")
            startActivity(intent)
        }
    }


}
