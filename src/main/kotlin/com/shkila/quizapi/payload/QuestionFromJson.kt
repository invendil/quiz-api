package com.shkila.quizapi.payload

data class QuestionFromJson(
        var category: String? = null,
        var type: String? = null,
        var difficulty: String? = null,
        var question: String? = null,
        var correct_answer: String? = null,
        var incorrect_answers: Collection<String>? = listOf()
)
