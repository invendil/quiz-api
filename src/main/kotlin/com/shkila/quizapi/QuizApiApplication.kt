package com.shkila.quizapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories
@EntityScan(basePackageClasses = [QuizApiApplication::class, Jsr310JpaConverters::class])
open class QuizApiApplication

fun main(args: Array<String>) {
    runApplication<QuizApiApplication>(*args)
}

