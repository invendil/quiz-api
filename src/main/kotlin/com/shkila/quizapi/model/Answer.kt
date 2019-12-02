package com.shkila.quizapi.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "answers")
data class Answer(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", unique = true, nullable = false)
        val id: Long? = null,

        @Column(name = "is_right", nullable = false)
        var right: Boolean? = null,

        @Column(name = "description", nullable = false)
        var description: String? = null,

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "question_id", nullable = false)
        var question: Question? = null
)
