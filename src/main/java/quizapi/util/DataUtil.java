package quizapi.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import quizapi.model.Answer;
import quizapi.model.Category;
import quizapi.model.Question;
import quizapi.payload.AnswerPayload;
import quizapi.payload.CategoryPayload;
import quizapi.payload.QuestionFromJson;
import quizapi.payload.QuestionPayload;
import quizapi.repository.CategoryRepository;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;

public class DataUtil {

    public static ObjectMapper objectMapper = new ObjectMapper();

    private static Random random = new Random();

    private List<String> difficulties = Arrays.asList("easy", "medium", "hard");

    private Map<String, Category> categoryMap = new HashMap<>();

    public List<Question> getAnswersFromJson(List<Category> categories) throws IOException {

        for(Category category: categories){
            categoryMap.put(category.getName(), category);
        }
        List<QuestionFromJson> questionsFromJson = objectMapper
                .readValue(
                        new File("./src/main/resources/json/gameList.json"),
                        new TypeReference<List<QuestionFromJson>>(){}
                );
        List<Question> questions = new ArrayList<>();
        for(QuestionFromJson q: questionsFromJson){
            questions.add(mapJsonToDataQuestion(q));
        }

        return questions;
    }

    public Question mapJsonToDataQuestion(QuestionFromJson questionFromJson){
        Question question = new Question();
        List<Answer> answers = new ArrayList(4);
        answers.add(new Answer(true, questionFromJson.correct_answer, question));
        for(String answer: questionFromJson.incorrect_answers){
            answers.add(new Answer(false, answer, question));
        }
        Collections.shuffle(answers);
        question.setAnswers(answers);
        question.setDifficulty(difficulties.get(random.nextInt(3)));
        question.setCategory(categoryMap.get(questionFromJson.category));
        question.setDescription(questionFromJson.question);
        return question;
    }

    public QuestionPayload mapModelToPayloadQuestion(Question question) {
        List<AnswerPayload> answerPayloads = new ArrayList<>();
        question.getAnswers().forEach(answer -> {
            answerPayloads.add(new AnswerPayload(
                    answer.getId(),
                    answer.getDescription()
            ));

        });
        QuestionPayload questionPayload = new QuestionPayload(
                question.getId(),
                new CategoryPayload(question.getCategory().getName()),
                question.getDescription(),
                question.getDifficulty(),
                answerPayloads
        );
        return questionPayload;
    }
}
