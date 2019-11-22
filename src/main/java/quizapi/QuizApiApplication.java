package quizapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import quizapi.service.InitService;

import javax.sql.DataSource;
import java.io.IOException;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan(basePackageClasses = {
		QuizApiApplication.class,
		Jsr310JpaConverters.class
})
public class QuizApiApplication {

	@Autowired
	private InitService initService;

	public static void main(String[] args) {
		SpringApplication.run(QuizApiApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void initDb() throws IOException {
		initService.initCategories();
		initService.initQuestions();
	}
}
