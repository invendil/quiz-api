package com.shkila.quizapi.repository

import com.shkila.quizapi.model.Answer
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AnswerRepository : CrudRepository<Answer, Long>
