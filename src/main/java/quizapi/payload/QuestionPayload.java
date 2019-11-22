package quizapi.payload;

import java.util.Collection;
import java.util.List;

public class QuestionPayload {
    public Long id;

    public CategoryPayload category;

    public String description;

    public String difficulty;

    public List<AnswerPayload> answers;

    public QuestionPayload() {
    }

    public QuestionPayload(Long id, CategoryPayload category, String description, String difficulty, List<AnswerPayload> answers) {
        this.id = id;
        this.category = category;
        this.description = description;
        this.difficulty = difficulty;
        this.answers = answers;
    }
}
