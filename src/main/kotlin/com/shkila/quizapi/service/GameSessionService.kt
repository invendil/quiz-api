package com.shkila.quizapi.service

import com.shkila.quizapi.exception.ResourceNotFoundException
import com.shkila.quizapi.model.Answer
import com.shkila.quizapi.model.GameSession
import com.shkila.quizapi.model.SessionAnswer
import com.shkila.quizapi.payload.CategoryPayload
import com.shkila.quizapi.payload.GameSessionPayload
import com.shkila.quizapi.payload.QuestionPayload
import com.shkila.quizapi.repository.AnswerRepository
import com.shkila.quizapi.repository.CategoryRepository
import com.shkila.quizapi.repository.GameSessionRepository
import com.shkila.quizapi.util.mapModelToPayloadQuestion
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GameSessionService {

    @Autowired
    lateinit var gameSessionRepository: GameSessionRepository
    @Autowired
    lateinit var categoryRepository: CategoryRepository
    @Autowired
    lateinit var userService: UserService
    @Autowired
    lateinit var answerRepository: AnswerRepository

    fun getCategoriesPayload(): Collection<CategoryPayload> = categoryRepository
            .findCategoriesNames()
            .map { CategoryPayload(name = it) }

    fun findById(id: Long): GameSession = gameSessionRepository
            .findById(id)
            .orElseThrow { ResourceNotFoundException("GameSession", "id", id) }


    fun startGame(gameSessionPayload: GameSessionPayload): List<QuestionPayload> {
        val currentSessionCategory = categoryRepository
                .findByName(gameSessionPayload.category!!)
                .orElseThrow { ResourceNotFoundException("Category", "name", gameSessionPayload.category!!) }
        val currentSessionUser = userService.findOrCreateByUsername(gameSessionPayload.username!!)
        val gameSession = GameSession(
                id = 0,
                score = 0,
                // TODO change on db request
                questionsCountTotal =
                if (currentSessionCategory.questions!!.size < gameSessionPayload.questionsCount)
                    currentSessionCategory.questions!!.size
                else gameSessionPayload.questionsCount,
                category = currentSessionCategory,
                user = currentSessionUser,
                sessionAnswers = mutableListOf()
        )
        gameSessionRepository.save(gameSession)

        return currentSessionCategory.questions!!
                .shuffled()
                .subList(0, gameSession.questionsCountTotal!!)
                .map { it.mapModelToPayloadQuestion() }
    }

    fun isAnswerRight(gameSessionPayload: GameSessionPayload): Boolean {
        val currentSessionUser = userService.findByUsername(gameSessionPayload.username!!)
        val currentGameSession = gameSessionRepository
                .findByUser(currentSessionUser)
                .orElseThrow { ResourceNotFoundException("GameSession", "user", currentSessionUser.username!!) }
        val currentAnswer = answerRepository
                .findById(gameSessionPayload.answerId)
                .orElseThrow { ResourceNotFoundException("GameSession", "answer", gameSessionPayload.answerId) }
        // update current session by getting answer
        updateGameSession(currentGameSession, currentAnswer)
        return currentAnswer.right!!
    }

    private fun updateGameSession(currentGameSession: GameSession, currentAnswer: Answer) {

        currentGameSession.sessionAnswers!!.add(
                SessionAnswer(
                        gameSession = currentGameSession,
                        answer = currentAnswer
                )
        )

        currentGameSession.questionsCountAnswered++

        if (currentAnswer.right!!) {
            currentGameSession.score++
        }

        // todo implement record logic
        if (currentGameSession.sessionAnswers!!.size == currentGameSession.questionsCountTotal)
            gameSessionRepository.delete(currentGameSession)
        else
            gameSessionRepository.save(currentGameSession)

    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(GameSessionService::class.java)
    }
}
