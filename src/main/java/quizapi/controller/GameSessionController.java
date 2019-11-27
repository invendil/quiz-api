package quizapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import quizapi.model.Category;
import quizapi.model.Question;
import quizapi.payload.CategoryPayload;
import quizapi.payload.GameSessionPayload;
import quizapi.payload.QuestionPayload;
import quizapi.service.GameSessionService;
import quizapi.service.InitService;
import quizapi.service.QuestionService;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/game_session/")
public class GameSessionController {

    @Autowired
    private GameSessionService gameSessionService;
    @Autowired
    private InitService initService;

    private static final Logger logger = LoggerFactory.getLogger(GameSessionController.class);

    @PostMapping("start")
    public @ResponseBody Collection<QuestionPayload> startGameSession(@RequestBody GameSessionPayload gameSessionPayload) {
        return gameSessionService.startGame(gameSessionPayload);
    }

    @GetMapping("categories")
    public @ResponseBody Iterable<CategoryPayload> getCategories() {
        return gameSessionService.getCategoriesPayload();
    }

    @PostMapping("ask")
    public @ResponseBody Boolean getCategories(@RequestBody GameSessionPayload gameSessionPayload) {
        return gameSessionService.isAnswerRight(gameSessionPayload);
    }

}
