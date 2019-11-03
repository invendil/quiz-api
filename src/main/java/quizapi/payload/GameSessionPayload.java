package quizapi.payload;

public class GameSessionPayload {

    private String category;

    private String username;

    private int answer;

    public GameSessionPayload() {
    }

    public GameSessionPayload(String category, String username, int answer) {
        this.category = category;
        this.username = username;
        this.answer = answer;
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
}
