package quizapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quizapi.exception.ResourceNotFoundException;
import quizapi.model.*;
import quizapi.payload.CategoryPayload;
import quizapi.payload.GameSessionPayload;
import quizapi.payload.QuestionPayload;
import quizapi.repository.AnswerRepository;
import quizapi.repository.CategoryRepository;
import quizapi.repository.GameSessionRepository;
import quizapi.util.DataUtil;

import java.util.*;

@Service
public class GameSessionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameSessionService.class);

    private static DataUtil dataUtil = new DataUtil();

    @Autowired
    private GameSessionRepository gameSessionRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private AnswerRepository answerRepository;

    public GameSession findById(long id) {
        return gameSessionRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("GameSession", "id", id));
    }

    public List<QuestionPayload> startGame(GameSessionPayload gameSessionPayload) {

        Category currentSessionCategory = categoryRepository
                .findByName(gameSessionPayload.getCategory())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "name", gameSessionPayload.getCategory()));
        User currentSessionUser = userService.findOrCreateByUsername(gameSessionPayload.getUsername());
        GameSession gameSession = new GameSession(
                0,
                0,
                // TODO change on db request
                currentSessionCategory.getQuestions().size(),
                currentSessionCategory,
                currentSessionUser,
                new HashSet<>()
        );
        gameSessionRepository.save(gameSession);

        List<QuestionPayload> questionPayloads = new ArrayList<>();
        for (Question question : currentSessionCategory.getQuestions()) {
            questionPayloads.add(dataUtil.mapModelToPayloadQuestion(question));
        }
        // TODO take questions count from client side
        return questionPayloads.subList(0, 5);
    }

    public Boolean isAnswerRight(Long answerId) {
        Answer answer = answerRepository
                .findById(answerId)
                .orElseThrow(() -> new ResourceNotFoundException("GameSession", "answer", answerId));
        return answer.getRight();
    }

    public Boolean isAnswerRight(GameSessionPayload gameSessionPayload) {
        User currentSessionUser = userService.findByUsername(gameSessionPayload.getUsername());
        GameSession currentGameSession = gameSessionRepository
                .findByUser(currentSessionUser)
                .orElseThrow(() -> new ResourceNotFoundException("GameSession", "user", currentSessionUser.getUsername()));
        Answer currentAnswer = answerRepository
                .findById(gameSessionPayload.getAnswerId())
                .orElseThrow(() -> new ResourceNotFoundException("GameSession", "answer", gameSessionPayload.getAnswerId()));
        // update current session by getting answer
        updateGameSessin(currentGameSession, currentAnswer);
        return currentAnswer.getRight();
    }

    public Collection<CategoryPayload> getCategoriesPayload() {
        List<CategoryPayload> categoryPayloads = new ArrayList<>();
        for (String category : categoryRepository.findCategoriesNames()) {
            categoryPayloads.add(new CategoryPayload(category));
        }
        return categoryPayloads;
    }

    private void updateGameSessin(GameSession currentGameSession, Answer currentAnswer) {
        // TODO take questions count from client side and change session handler
        if (currentGameSession.getSessionAnswers().size() == 4) {
            gameSessionRepository.delete(currentGameSession);
        } else {
            int score = currentGameSession.getScore();
            Set<SessionAnswer> sessionAnswers = currentGameSession.getSessionAnswers();
            int currentAnsweredQuestionsCount = currentGameSession.getQuestionsCountAnswered();
            sessionAnswers.add(new SessionAnswer(
                    currentGameSession,
                    currentAnswer
            ));
            currentGameSession.setQuestionsCountAnswered(++currentAnsweredQuestionsCount);
            if (currentAnswer.getRight()) {
                currentGameSession.setScore(++score);
            }
            gameSessionRepository.save(currentGameSession);
        }
    }
}
