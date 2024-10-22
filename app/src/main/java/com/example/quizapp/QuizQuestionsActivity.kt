package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity() {

    private var userName: String? = null
    private var category: String? = null
    private var questionsList: ArrayList<Question> = ArrayList()
    private var currentQuestionIndex = 0
    private var selectedAlternativeIndex = -1
    private var isAnswerChecked = false
    private var totalScore = 0

    private val alternativesIds = arrayOf(R.id.optionOne, R.id.optionTwo, R.id.optionThree, R.id.optionFour)

    private var tvQuestion: TextView? = null
    private var ivImage: ImageView? = null
    private var progressBar: ProgressBar? = null
    private var tvProgress: TextView? = null
    private var btnSubmit: Button? = null
    private var tvAlternatives: ArrayList<TextView>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        // Get the user name and category from the intent
        userName = intent.getStringExtra(Constants.USER_NAME)
        category = intent.getStringExtra(Constants.QUIZ_CATEGORY)

        // Fetch the questions based on the selected category
        if (category != null) {
            questionsList = Constants.getQuestionsByCategory(category)
        } else {
            // Handle the case where category is null (optional)
            Toast.makeText(this, "Category not found!", Toast.LENGTH_SHORT).show()
            finish() // Exit the activity if the category is null
        }

        // Initialize UI elements
        tvQuestion = findViewById(R.id.tvQuestion)
        ivImage = findViewById(R.id.ivImage)
        progressBar = findViewById(R.id.progressBar)
        tvProgress = findViewById(R.id.tvProgress)
        btnSubmit = findViewById(R.id.btnSubmit)
        tvAlternatives = arrayListOf(
            findViewById(R.id.optionOne),
            findViewById(R.id.optionTwo),
            findViewById(R.id.optionThree),
            findViewById(R.id.optionFour)
        )

        updateQuestion()

        // Handle Submit button click
        btnSubmit?.setOnClickListener {
            if (!isAnswerChecked) {
                val anyAnswerIsChecked = selectedAlternativeIndex != -1
                if (!anyAnswerIsChecked) {
                    Toast.makeText(this, "Please, select an alternative", Toast.LENGTH_SHORT).show()
                } else {
                    val currentQuestion = questionsList[currentQuestionIndex]
                    if (selectedAlternativeIndex == currentQuestion.correctAnswerIndex) {
                        answerView(tvAlternatives!![selectedAlternativeIndex], R.drawable.correct_option_border_bg)
                        totalScore++
                    } else {
                        answerView(tvAlternatives!![selectedAlternativeIndex], R.drawable.wrong_option_border_bg)
                        answerView(tvAlternatives!![currentQuestion.correctAnswerIndex], R.drawable.correct_option_border_bg)
                    }

                    isAnswerChecked = true
                    btnSubmit?.text = if (currentQuestionIndex == questionsList.size - 1) "FINISH" else "GO TO NEXT QUESTION"
                    selectedAlternativeIndex = -1
                }
            } else {
                if (currentQuestionIndex < questionsList.size - 1) {
                    currentQuestionIndex++
                    updateQuestion()
                } else {
                    // Move to the ResultActivity at the end of the quiz
                    val intent = Intent(this, ResultActivity::class.java)
                    intent.putExtra(Constants.USER_NAME, userName)
                    intent.putExtra(Constants.TOTAL_QUESTIONS, questionsList.size)
                    intent.putExtra(Constants.SCORE, totalScore)
                    startActivity(intent)
                    finish()
                }

                isAnswerChecked = false
            }
        }

        // Handle option clicks
        tvAlternatives?.let {
            for (optionIndex in it.indices) {
                it[optionIndex].setOnClickListener {
                    if (!isAnswerChecked) {
                        selectedAlternativeView(it as TextView, optionIndex)
                    }
                }
            }
        }
    }

    private fun updateQuestion() {
        defaultAlternativesView()

        // Set Question Text
        tvQuestion?.text = questionsList[currentQuestionIndex].questionText
        // Set Question Image
        ivImage?.setImageResource(questionsList[currentQuestionIndex].image)
        // Update Progress Bar
        progressBar?.progress = currentQuestionIndex + 1
        // Update Progress Text
        tvProgress?.text = "${currentQuestionIndex + 1}/${questionsList.size}"

        // Set alternatives text
        for (alternativeIndex in questionsList[currentQuestionIndex].alternatives.indices) {
            tvAlternatives!![alternativeIndex].text = questionsList[currentQuestionIndex].alternatives[alternativeIndex]
        }

        btnSubmit?.text = if (currentQuestionIndex == questionsList.size - 1) "FINISH" else "SUBMIT"
    }

    private fun defaultAlternativesView() {
        for (alternativeTv in tvAlternatives!!) {
            alternativeTv.typeface = Typeface.DEFAULT
            alternativeTv.setTextColor(Color.parseColor("#7A8089"))
            alternativeTv.background = ContextCompat.getDrawable(
                this@QuizQuestionsActivity,
                R.drawable.default_option_border_bg
            )
        }
    }

    private fun selectedAlternativeView(option: TextView, index: Int) {
        defaultAlternativesView()
        selectedAlternativeIndex = index

        option.setTextColor(
            Color.parseColor("#363A43")
        )
        option.setTypeface(option.typeface, Typeface.BOLD)
        option.background = ContextCompat.getDrawable(
            this@QuizQuestionsActivity,
            R.drawable.selected_option_border_bg
        )
    }

    private fun answerView(view: TextView, drawableId: Int) {
        view.background = ContextCompat.getDrawable(
            this@QuizQuestionsActivity,
            drawableId
        )
        view.setTextColor(Color.WHITE) // Make the text color white on selection
    }
}
