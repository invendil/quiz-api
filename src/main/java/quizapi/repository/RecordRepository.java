package quizapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import quizapi.modal.Record;
import quizapi.modal.User;

@Repository
public interface RecordRepository extends CrudRepository<Record, Long> {}
