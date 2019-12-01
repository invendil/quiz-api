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
@Table(name = "session_answers")
data class SessionAnswer(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", unique = true, nullable = false)
        val id: Long? = null,

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "game_session_id", nullable = false)
        var gameSession: GameSession? = null,

        @ManyToOne(targetEntity = Answer::class)
        var answer: Answer? = null
)