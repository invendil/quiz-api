package quizapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import quizapi.modal.Category;
import quizapi.modal.GameSession;
import quizapi.modal.User;

import java.util.Optional;

@Repository
public interface GameSessionRepository extends CrudRepository<GameSession, Long> {
    Optional<GameSession> findByUser(User user);
}
