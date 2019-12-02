package com.shkila.quizapi.repository

import com.shkila.quizapi.model.Category
import com.shkila.quizapi.model.Record
import com.shkila.quizapi.model.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface RecordRepository : CrudRepository<Record, Long> {
    fun findByUser(user: User): Optional<Record>
    fun findAllByCategory(category: Category): Optional<List<Record>>
}
