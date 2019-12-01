package com.shkila.quizapi.repository

import com.shkila.quizapi.model.GameSession
import com.shkila.quizapi.model.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface GameSessionRepository : CrudRepository<GameSession, Long> {
    fun findByUser(user: User): Optional<GameSession>
}
