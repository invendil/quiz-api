package com.shkila.quizapi.model


import javax.persistence.CascadeType.PERSIST
import javax.persistence.CascadeType.REFRESH
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "questions")
data class Question(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", unique = true, nullable = false)
        val id: Long? = null,

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "category_id", nullable = false)
        var category: Category? = null,

        @Column(name = "description", nullable = false)
        var description: String? = null,

        @Column(name = "difficulty", nullable = false)
        var difficulty: String? = null,

        @OneToMany(cascade = [PERSIST, REFRESH], targetEntity = Answer::class, mappedBy = "question")
        var answers: Collection<Answer>? = null
)
