package com.shkila.quizapi.util

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.shkila.quizapi.model.Answer
import com.shkila.quizapi.model.Category
import com.shkila.quizapi.model.Question
import com.shkila.quizapi.payload.AnswerPayload
import com.shkila.quizapi.payload.CategoryPayload
import com.shkila.quizapi.payload.QuestionFromJson
import com.shkila.quizapi.payload.QuestionPayload
import java.io.File
import java.io.IOException
import java.util.ArrayList
import java.util.Arrays
import java.util.HashMap
import java.util.Random


var objectMapper = ObjectMapper()

private val random = Random()


private val difficulties = Arrays.asList("easy", "medium", "hard")

private val categoryMap = HashMap<String, Category>()

@Throws(IOException::class)
fun getAnswersFromJson(categories: List<Category>): List<Question> {

    categories.forEach { category ->
        categoryMap[category.name!!] = category
    }
    val questionsFromJson = objectMapper
            .readValue(
                    File("./src/main/resources/json/gameList.json"),
                    object : TypeReference<List<QuestionFromJson>>() {}
            )
    val questions = ArrayList<Question>()
    questionsFromJson.forEach {
        questions.add(it.mapJsonToDataQuestion())
    }

    return questions
}

fun QuestionFromJson.mapJsonToDataQuestion(): Question = run {
    val question = Question()
    val answers = mutableListOf(
            Answer(
                    right = true,
                    description = this.correct_answer,
                    question = question
            )
    )
    this.incorrect_answers!!.forEach { answer ->
        answers.add(
                Answer(
                        right = false,
                        description = answer,
                        question = question
                )
        )
    }
    answers.shuffle()
    question.apply {
        this.answers = answers
        difficulty = difficulties[random.nextInt(3)]
        category = categoryMap[this@run.category]
        description = this@run.question
    }
    question
}

fun Question.mapModelToPayloadQuestion(): QuestionPayload =
        QuestionPayload(
                id = this.id,
                category = CategoryPayload(this.category!!.name),
                description = this.description,
                difficulty = this.difficulty,
                answers = this.answers!!.map {
                    AnswerPayload(id = it.id, description = it.description)
                }
        )


