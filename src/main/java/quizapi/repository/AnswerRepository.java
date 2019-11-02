package quizapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import quizapi.modal.Answer;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, Long> {}
