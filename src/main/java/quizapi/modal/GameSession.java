package quizapi.modal;

import javax.persistence.*;

@Entity
@Table(name = "game_sessions")
public class GameSession {

    public GameSession() {
    }

    public GameSession(Category category, User user, Integer questionsCountTotal, Integer questionsCountAnswered, Integer score) {
        this.score = score;
        this.category = category;
        this.user = user;
        this.questionsCountTotal = questionsCountTotal;
        this.questionsCountAnswered = questionsCountAnswered;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "score", nullable = false)
    private Integer score;

    @ManyToOne(targetEntity = Category.class, optional = false)
    private Category category;

    @ManyToOne(targetEntity = User.class, optional = false)
    private User user;

    @Column(name = "questions_count_total", nullable = false)
    private Integer questionsCountTotal;

    @Column(name = "questions_count_answered", nullable = false)
    private Integer questionsCountAnswered;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getQuestionsCountTotal() {
        return questionsCountTotal;
    }

    public void setQuestionsCountTotal(Integer questionsCountTotal) {
        this.questionsCountTotal = questionsCountTotal;
    }

    public Integer getQuestionsCountAnswered() {
        return questionsCountAnswered;
    }

    public void setQuestionsCountAnswered(Integer questionsCountAnswered) {
        this.questionsCountAnswered = questionsCountAnswered;
    }
}
