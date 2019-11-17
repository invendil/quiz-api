package quizapi.payload;

public class GameSessionPayload {

    private String category;

    private String username;

    private long answerId;

    private int questionsCount;

    public GameSessionPayload() {
    }

    public GameSessionPayload(String category, String username, long answerId, int questionsCount) {
        this.category = category;
        this.username = username;
        this.answerId = answerId;
        this.questionsCount = questionsCount;
    }

    public long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getQuestionsCount() {
        return questionsCount;
    }

    public void setQuestionsCount(int questionsCount) {
        this.questionsCount = questionsCount;
    }
}
