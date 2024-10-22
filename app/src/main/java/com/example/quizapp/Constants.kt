package com.example.quizapp

object Constants {
    val USER_NAME: String = "user_name"
    val TOTAL_QUESTIONS: String = "total_questions"
    val SCORE: String = "score"
    val QUIZ_CATEGORY: String = "quiz_category"

    private val answeredQuestions = HashSet<Int>()

    // Function to get questions based on category
    fun getQuestionsByCategory(category: String?): ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        // Create category-based questions
        val allQuestions = mapOf(
            "History" to arrayListOf(
                Question(
                    1,
                    "Who was the first Indian to win the Nobel Prize?",
                    R.drawable.que4,
                    arrayListOf("Rabindranath Tagore", "C.V. Raman", "Mother Teresa", "Amartya Sen"),
                    0
                ),
                Question(
                    2,
                    "In which year was the Indian National Congress founded?",
                    R.drawable.inc,
                    arrayListOf("1885", "1892", "1905", "1911"),
                    0
                ),
                Question(
                    3,
                    "Who was the founder of the Maurya Empire?",
                    R.drawable.empire,
                    arrayListOf("Ashoka", "Chandragupta Maurya", "Bindusara", "Harsha"),
                    1
                ),

            ),
            "Geography" to arrayListOf(
                Question(
                    1,
                    "Which Indian state has the highest literacy rate as per the 2011 Census?",
                    R.drawable.que1,
                    arrayListOf("Kerala", "Mizoram", "Tripura", "Goa"),
                    0
                ),
                Question(
                    2,
                    "Which Indian state has the largest coastline?",
                    R.drawable.coastline,
                    arrayListOf("Gujarat", "Tamil Nadu", "Andhra Pradesh", "Maharashtra"),
                    0
                ),
                Question(
                    3,
                    "Which Indian state is known as the “Land of Five Rivers”?",
                    R.drawable.rivers,
                    arrayListOf("Gujarat", "Punjab", "Haryana", "Rajasthan"),
                    0
                ),

            ),
            "Space" to arrayListOf(
                Question(
                    1,
                    "Who was the first Indian to go into space?",
                    R.drawable.que2,
                    arrayListOf("Rakesh Sharma", "Kalpana Chawla", "Sunita Williams", "Ravish Malhotra"),
                    0
                ),
                Question(
                    2,
                    "Which organization is responsible for India's space research and satellite launches?",
                    R.drawable.isro,
                    arrayListOf("DRDO", "NASA", "ISRO", "HAL"),
                    2
                ),
                Question(
                    3,
                    "In which year did India launch its first mission to Mars, Mangalyaan (Mars Orbiter Mission)?",
                    R.drawable.mangalyaan,
                    arrayListOf("2010", "2013", "2016", "2018"),
                    1
                ),

            ),
            "Sports" to arrayListOf(
                Question(
                    1,
                    "Who was the first Indian to win an individual Olympic gold medal?",
                    R.drawable.gold,
                    arrayListOf("Abhinav Bindra", "Milkha Singh", "P. V. Sindhu", "Sushil Kumar"),
                    0
                ),
                Question(
                    2,
                    "Which city hosted the 2010 Commonwealth Games in India?",
                    R.drawable.commonwealth,
                    arrayListOf("Mumbai", "Kolkata", "New Delhi", "Chennai"),
                    2
                ),
                Question(
                    3,
                    "In which sport is the Santosh Trophy awarded?",
                    R.drawable.trophy,
                    arrayListOf("Cricket", "Hockey", "Football", "Badminton"),
                    2
                ),

            ),

            "Entertainment" to arrayListOf(
                Question(
                    1,
                    "Which movie won the Oscar for Best Picture in 2020?",
                    R.drawable.oscar,
                    arrayListOf("Joker", "Parasite", "1917", "Once Upon a Time in Hollywood"),
                    1
                ),
                Question(
                    2,
                    "Who is the king of pop ?",
                    R.drawable.pop,
                    arrayListOf("Elvis Presley", "Michael Jackson", "Justin Bieber", "Prince"),
                    1
                ),
                Question(
                    3,
                    "Which TV show features the characters Sheldon Cooper and Leonard Hofstadter?",
                    R.drawable.tv,
                    arrayListOf("Friends", "The Big Bang Theory", "How I Met Your Mother", "The Big Bang Theory"),
                    1
                ),
            ),

            "Reasoning" to arrayListOf(
                Question(
                    1,
                    "What is the next number in the sequence: 2, 6, 12, 20, 30, ...?",
                    R.drawable.sequence,
                    arrayListOf("40", "42", "50", "60"),
                    0
                ),
                Question(
                    2,
                    "If P is the brother of Q and R is the sister of Q, what is the relationship of P to R?",
                    R.drawable.relationship,
                    arrayListOf("Father", "Uncle", "Brother", "Cousin"),
                    2
                ),
                Question(
                    3,
                    "Which word will come next in the series: Cat, Dog, Elephant, Giraffe, ...?",
                    R.drawable.animals,
                    arrayListOf("Lion", "Monkey", "Horse", "Tiger"),
                    0
                ),
            )
        )

        // Filter out the questions that belong to the selected category
        val categoryQuestions = allQuestions[category] ?: emptyList()

        // Filter out already answered questions
        val unansweredQuestions = categoryQuestions.filter { !answeredQuestions.contains(it.id) }

        // Shuffle and add to the final questions list
        unansweredQuestions.shuffled().take(10).forEach {
            questionsList.add(it)
        }

        return questionsList
    }

    // Function to mark a question as answered
    fun markQuestionAsAnswered(questionId: Int) {
        answeredQuestions.add(questionId)
    }
}
