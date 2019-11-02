package quizapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import quizapi.modal.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {}
