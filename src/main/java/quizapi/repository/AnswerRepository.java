package quizapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import quizapi.model.Answer;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, Long> {

}
