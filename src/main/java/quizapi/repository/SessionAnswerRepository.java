package quizapi.repository;

import org.springframework.data.repository.CrudRepository;
import quizapi.model.SessionAnswer;

public interface SessionAnswerRepository extends CrudRepository<SessionAnswer, Long> {

}
