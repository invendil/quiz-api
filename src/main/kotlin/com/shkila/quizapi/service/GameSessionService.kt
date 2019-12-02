package com.shkila.quizapi.service

import com.shkila.quizapi.exception.ResourceNotFoundException
import com.shkila.quizapi.model.Answer
import com.shkila.quizapi.model.GameSession
import com.shkila.quizapi.model.Record
import com.shkila.quizapi.model.SessionAnswer
import com.shkila.quizapi.payload.CategoryPayload
import com.shkila.quizapi.payload.GameSessionPayload
import com.shkila.quizapi.payload.QuestionPayload
import com.shkila.quizapi.repository.AnswerRepository
import com.shkila.quizapi.repository.CategoryRepository
import com.shkila.quizapi.repository.GameSessionRepository
import com.shkila.quizapi.repository.RecordRepository
import com.shkila.quizapi.util.mapToPayload
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
    lateinit var recordRepository: RecordRepository
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
                .map { it.mapToPayload() }
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
        currentGameSession.updateGameSession(currentAnswer)
        return currentAnswer.right!!
    }

    private fun GameSession.updateGameSession(currentAnswer: Answer) = run {

        this.sessionAnswers!!.add(
                SessionAnswer(
                        gameSession = this,
                        answer = currentAnswer
                )
        )

        this.questionsCountAnswered++

        if (currentAnswer.right!!) {
            this.score++
        }

        // todo implement record logic
        if (this.sessionAnswers!!.size == this.questionsCountTotal) {
            gameSessionRepository.delete(this)
            this.recordGameSession()
        } else {
            gameSessionRepository.save(this)
        }
    }

    private fun GameSession.recordGameSession() = also {session ->
        val record = recordRepository
                .findByUser(session.user!!)
                .orElseGet { null }
        if (record != null) {
            if (session isBetterThan record) {
                recordRepository.save(
                        record.apply {
                            score = session.score
                            questionCount = session.questionsCountTotal
                            category = session.category
                            user = session.user
                        }
                )
            }
        } else {
            recordRepository.save(
                    Record (
                        score = session.score,
                        questionCount = session.questionsCountTotal,
                        category = session.category,
                        user = session.user
                    )
            )
        }
    }

    private infix fun GameSession.isBetterThan(record: Record): Boolean =
            ((this.score.toFloat() / this.questionsCountTotal!!) > (record.score!!.toFloat() / record.questionCount!!))

    companion object {
        private val LOGGER = LoggerFactory.getLogger(GameSessionService::class.java)
    }
}
