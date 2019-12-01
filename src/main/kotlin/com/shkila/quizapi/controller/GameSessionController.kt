package com.shkila.quizapi.controller

import com.shkila.quizapi.payload.CategoryPayload
import com.shkila.quizapi.payload.GameSessionPayload
import com.shkila.quizapi.payload.QuestionPayload
import com.shkila.quizapi.service.GameSessionService
import com.shkila.quizapi.service.InitService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/game_session/")
class GameSessionController {

    @Autowired
    lateinit var gameSessionService: GameSessionService
    @Autowired
    lateinit var initService: InitService

    @GetMapping("categories")
    @ResponseBody
    fun getCategories(): Collection<CategoryPayload> = gameSessionService.getCategoriesPayload()

    @PostMapping("start")
    @ResponseBody
    fun startGameSession(@RequestBody gameSessionPayload: GameSessionPayload): Collection<QuestionPayload> =
            gameSessionService.startGame(gameSessionPayload)

    @PostMapping("ask")
    @ResponseBody
    fun verifyAnswer(@RequestBody gameSessionPayload: GameSessionPayload): Boolean =
            gameSessionService.isAnswerRight(gameSessionPayload)

    companion object {
        private val logger = LoggerFactory.getLogger(GameSessionController::class.java)
    }

}
