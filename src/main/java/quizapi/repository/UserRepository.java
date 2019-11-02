package quizapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import quizapi.modal.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {}
