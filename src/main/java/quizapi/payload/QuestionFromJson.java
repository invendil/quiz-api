package quizapi.payload;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class QuestionFromJson {
    public String category;
    public String type;
    public String difficulty;
    public String question;
    public String correct_answer;
    public Collection<String> incorrect_answers = new ArrayList<>(3);

    public QuestionFromJson() {
    }

    public QuestionFromJson(String category, String type, String difficulty, String question, String correct_answer, Collection<String> incorrect_answers) {
        this.category = category;
        this.type = type;
        this.difficulty = difficulty;
        this.question = question;
        this.correct_answer = correct_answer;
        this.incorrect_answers = incorrect_answers;
    }
}
