package com.shkila.quizapi.model

import javax.persistence.CascadeType.PERSIST
import javax.persistence.CascadeType.REFRESH
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "categories")
data class Category(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", unique = true, nullable = false)
        val id: Long? = null,

        @Column(name = "name", nullable = false)
        var name: String? = null,

        @OneToMany(cascade = [PERSIST, REFRESH], targetEntity = Question::class, mappedBy = "category")
        var questions: Collection<Question>? = null,

        @OneToMany(cascade = [PERSIST, REFRESH], targetEntity = Record::class, mappedBy = "category")
        private val records: Collection<Record>? = null
)
