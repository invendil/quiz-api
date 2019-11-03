package quizapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import quizapi.model.Record;

@Repository
public interface RecordRepository extends CrudRepository<Record, Long> {}
