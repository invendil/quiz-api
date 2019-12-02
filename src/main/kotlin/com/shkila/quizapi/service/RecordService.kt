package com.shkila.quizapi.service

import com.shkila.quizapi.model.Category
import com.shkila.quizapi.model.Record
import com.shkila.quizapi.payload.RecordPayload
import com.shkila.quizapi.repository.CategoryRepository
import com.shkila.quizapi.repository.QuestionRepository
import com.shkila.quizapi.repository.RecordRepository
import com.shkila.quizapi.util.getAnswersFromJson
import com.shkila.quizapi.util.mapToPayload
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.IOException
import java.util.ArrayList
import java.util.Arrays

@Service
class RecordService {
    @Autowired
    lateinit var recordRepository: RecordRepository
    @Autowired
    lateinit var categoryRepository: CategoryRepository

    fun getByCategoryName(categoryName: String): Collection<RecordPayload>? = run {
        val category = categoryRepository
                .findByName(categoryName)
                .orElseGet { null }
        recordRepository
                .findAllByCategory(category)
                .orElseGet { null }
                ?.map { it.mapToPayload() }
    }
}
