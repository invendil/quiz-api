package com.shkila.quizapi.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "users")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", unique = true, nullable = false)
        val id: Long? = null,

        @Column(name = "username", nullable = false)
        var username: String? = null,

        @OneToMany(targetEntity = Answer::class, mappedBy = "id")
        var answers: Collection<Answer>? = null,

        @OneToMany(targetEntity = Record::class, mappedBy = "id")
        var records: Collection<Record>? = null
)
