package quizapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import quizapi.modal.Question;
import quizapi.modal.User;
import quizapi.payload.GameSessionPayload;
import quizapi.service.GameSessionService;
import quizapi.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GameSessionController {

    @Autowired
    private GameSessionService gameSessionService;


    private static final Logger logger = LoggerFactory.getLogger(GameSessionController.class);

    @GetMapping("/start_game_session")
    public @ResponseBody
    List<Question> getCurrentUser(@ResponseBody GameSessionPayload gameSessionPayload) {
        gameSessionService.startGame(gameSessionPayload);
        return userSummary;
    }

}
