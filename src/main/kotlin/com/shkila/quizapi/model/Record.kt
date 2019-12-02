package com.shkila.quizapi.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "records")
data class Record(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", unique = true, nullable = false)
        val id: Long? = null,

        @Column(name = "score", nullable = false)
        var score: Int? = null,

        @Column(name = "question_count", nullable = false)
        var questionCount: Int? = null,

        @ManyToOne(targetEntity = Category::class)
        var category: Category? = null,

        @ManyToOne(targetEntity = User::class)
        var user: User? = null
)