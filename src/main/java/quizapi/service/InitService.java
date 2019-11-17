package quizapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quizapi.model.Category;
import quizapi.model.Question;
import quizapi.repository.CategoryRepository;
import quizapi.repository.QuestionRepository;
import quizapi.util.DataUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class InitService {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionService.class);
    private static List<String> categoryNames = Arrays.asList("History", "VideoGames");
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public void initQuestions() throws IOException {
        List<Category> categories = (List<Category>) categoryRepository.findAll();
        if (questionRepository.count() == 0) {
            List<Question> questions = new DataUtil().getAnswersFromJson(categories);
            questionRepository.saveAll(questions);
        }
    }

    public void initCategories(){
        if(categoryRepository.count() == 0) {
            List<Category> categories = new ArrayList<>();
            for (String categoryName : categoryNames) {
                categories.add(new Category(categoryName, null));
            }
            categoryRepository.saveAll(categories);
        }
    }
}
