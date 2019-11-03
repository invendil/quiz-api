package quizapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quizapi.exception.ResourceNotFoundException;
import quizapi.model.*;
import quizapi.payload.GameSessionPayload;
import quizapi.repository.CategoryRepository;
import quizapi.repository.GameSessionRepository;

import java.util.ArrayList;
import java.util.Set;

@Service
public class GameSessionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameSessionService.class);

    @Autowired
    private GameSessionRepository gameSessionRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserService userService;

    public GameSession findById(long id) {
        return gameSessionRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("GameSession", "id", id));
    }

    public Category startGame(GameSessionPayload gameSessionPayload) {

        Category currentSessionCategory = categoryRepository
                .findByName(gameSessionPayload.getCategory())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "name", gameSessionPayload.getCategory()));
        User currentSessionUser = userService.findOrCreateByUsername(gameSessionPayload.getUsername());
        GameSession gameSession = new GameSession(
                0,
                0,
                currentSessionCategory.getQuestions().size(),
                currentSessionCategory,
                currentSessionUser,
                new ArrayList<Question>()
        );
        gameSessionRepository.save(gameSession);
        return currentSessionCategory;
    }

    public Boolean isAnswerRight(GameSessionPayload gameSessionPayload) {
        User currentSessionUser = userService .findByUsername(gameSessionPayload.getUsername());
        GameSession currentGameSession = gameSessionRepository
                .findByUser(currentSessionUser)
                .orElseThrow(() -> new ResourceNotFoundException("GameSession", "user", currentSessionUser.getUsername()));
        Set<SessionAnswer> answers = currentGameSession.getSessionAnswers();
        for(SessionAnswer answer : answers) {
            if(answer.getId().equals(gameSessionPayload.getAnswerId())) {
                return answer.getAnswer().getRight();
            }
        }
        LOGGER.debug("ERROR, ANSWER DON'T MATCH TO SESSION QUESTIONS!");
        //TODO Exception throw here
        return false;
    }
}
