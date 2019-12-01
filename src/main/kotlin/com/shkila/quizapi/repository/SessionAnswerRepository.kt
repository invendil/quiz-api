package com.shkila.quizapi.repository

import com.shkila.quizapi.model.SessionAnswer
import org.springframework.data.repository.CrudRepository

interface SessionAnswerRepository : CrudRepository<SessionAnswer, Long>
