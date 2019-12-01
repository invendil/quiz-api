package com.shkila.quizapi.repository

import com.shkila.quizapi.model.Category
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface CategoryRepository : CrudRepository<Category, Long> {
    fun findByName(name: String): Optional<Category>

    @Query("SELECT name FROM Category")
    fun findCategoriesNames(): List<String>
}