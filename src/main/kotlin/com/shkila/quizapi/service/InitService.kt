package com.shkila.quizapi.service

import com.shkila.quizapi.model.Category
import com.shkila.quizapi.repository.CategoryRepository
import com.shkila.quizapi.repository.QuestionRepository
import com.shkila.quizapi.util.getAnswersFromJson
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.IOException
import java.util.ArrayList
import java.util.Arrays

@Service
class InitService {
    @Autowired
    lateinit var questionRepository: QuestionRepository

    @Autowired
    lateinit var categoryRepository: CategoryRepository

    @Throws(IOException::class)
    fun initQuestions() {
        val categories = categoryRepository.findAll() as List<Category>
        if (questionRepository.count() == 0L) {
            val questions = getAnswersFromJson(categories)
            questionRepository.saveAll(questions)
        }
    }

    fun initCategories() {
        if (categoryRepository.count() == 0L) {
            val categories = ArrayList<Category>()
            categoryNames.forEach {
                categories.add(Category(name = it))
            }
            categoryRepository.saveAll(categories)
        }
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(QuestionService::class.java)
        private val categoryNames = listOf("History", "Video Games")
    }
}
