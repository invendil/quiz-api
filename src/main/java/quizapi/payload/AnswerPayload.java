package quizapi.payload;

public class AnswerPayload {
    public Long id;

    public String description;

    public AnswerPayload() {
    }

    public AnswerPayload(Long id, String description) {
        this.id = id;
        this.description = description;
    }
}
