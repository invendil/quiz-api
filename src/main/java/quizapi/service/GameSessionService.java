package quizapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quizapi.exception.ResourceNotFoundException;
import quizapi.modal.Category;
import quizapi.modal.GameSession;
import quizapi.modal.User;
import quizapi.payload.GameSessionPayload;
import quizapi.repository.CategoryRepository;
import quizapi.repository.GameSessionRepository;
import quizapi.repository.UserRepository;

@Service
public class GameSessionService {

    @Autowired
    private GameSessionRepository gameSessionRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserService userService;

    public GameSession findById(long id) {
        GameSession gameSession = gameSessionRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("GameSession", "id", id));
        return gameSession;
    }

    public Category startGame(GameSessionPayload gameSessionPayload) {

        Category currentSessionCategory = categoryRepository
                .findByName(gameSessionPayload.getCategory())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "name", gameSessionPayload.getCategory()));
        User currentSessionUser = userService.findOrCreateByUsername(gameSessionPayload.getUsername());
        GameSession gameSession = new GameSession(
                currentSessionCategory,
                currentSessionUser,
                currentSessionCategory.getQuestions().size(),
                0,
                0
        );
        gameSessionRepository.save(gameSession);
        return currentSessionCategory;
    }

    public Boolean giveAnswer(GameSessionPayload gameSessionPayload) {
        User currentSessionUser = userService.findOrCreateByUsername(gameSessionPayload.getUsername());
        GameSession currentGameSession = gameSessionRepository
                .findByUser(currentSessionUser)
                .orElseThrow(() -> new ResourceNotFoundException("GameSession", "user", currentSessionUser.getUsername()));
        List<Qusy>
    }
}
