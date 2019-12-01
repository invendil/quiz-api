package com.shkila.quizapi.repository

import com.shkila.quizapi.model.Question
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface QuestionRepository : CrudRepository<Question, Long>
