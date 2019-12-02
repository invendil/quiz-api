package com.shkila.quizapi.payload

data class RecordPayload(
        var score: Int? = null,
        var questionCount: Int? = null,
        var categoryName: String? = null,
        var username: String? = null
)