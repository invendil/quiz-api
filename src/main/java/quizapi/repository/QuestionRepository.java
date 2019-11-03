package quizapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import quizapi.model.Question;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {

}
