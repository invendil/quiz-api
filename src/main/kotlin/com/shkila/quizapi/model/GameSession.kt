package com.shkila.quizapi.model

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "game_sessions")
data class GameSession(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", unique = true, nullable = false)
        var id: Long? = null,

        @Column(name = "score", nullable = false)
        var score: Int = 0,

        @Column(name = "questions_count_total", nullable = false)
        var questionsCountTotal: Int? = null,

        @Column(name = "questions_count_answered", nullable = false)
        var questionsCountAnswered: Int = 0,

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "category_id", nullable = false)
        var category: Category? = null,

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "user_id", nullable = false)
        var user: User? = null,

        @ManyToMany(cascade = [CascadeType.ALL], targetEntity = SessionAnswer::class)
        var sessionAnswers: MutableCollection<SessionAnswer>? = null
)
