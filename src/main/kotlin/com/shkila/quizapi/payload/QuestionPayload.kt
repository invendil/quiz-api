package com.shkila.quizapi.payload

data class QuestionPayload(
        var id: Long? = null,
        var category: CategoryPayload? = null,
        var description: String? = null,
        var difficulty: String? = null,
        var answers: List<AnswerPayload>? = null
)
