package com.shkila.quizapi.repository

import com.shkila.quizapi.model.Record
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RecordRepository : CrudRepository<Record, Long>
