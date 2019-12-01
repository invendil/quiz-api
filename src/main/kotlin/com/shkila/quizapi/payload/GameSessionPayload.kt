package com.shkila.quizapi.payload

class GameSessionPayload(
        var category: String? = null,
        var username: String? = null,
        var answerId: Long = 0,
        var questionsCount: Int = 0
)
