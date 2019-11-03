package quizapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import quizapi.model.GameSession;
import quizapi.model.User;

import java.util.Optional;

@Repository
public interface GameSessionRepository extends CrudRepository<GameSession, Long> {
    Optional<GameSession> findByUser(User user);
}
