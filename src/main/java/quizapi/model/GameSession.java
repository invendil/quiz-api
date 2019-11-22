package quizapi.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "game_sessions")
public class GameSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "score", nullable = false)
    private Integer score;

    @Column(name = "questions_count_total", nullable = false)
    private Integer questionsCountTotal;

    @Column(name = "questions_count_answered", nullable = false)
    private Integer questionsCountAnswered;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany(cascade = {CascadeType.ALL}, targetEntity = SessionAnswer.class)
    private Set<SessionAnswer> sessionAnswers;

    public GameSession() {
    }

    public GameSession(Integer score, Integer questionsCountAnswered, Integer questionsCountTotal,  Category category, User user, Set<SessionAnswer> sessionAnswers) {
        this.score = score;
        this.questionsCountTotal = questionsCountTotal;
        this.questionsCountAnswered = questionsCountAnswered;
        this.category = category;
        this.user = user;
        this.sessionAnswers = sessionAnswers;
    }

    public Long getId() {
        return id;
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

    public Set<SessionAnswer> getSessionAnswers() {
        return sessionAnswers;
    }

    public void setSessionAnswers(Set<SessionAnswer> sessionAnswers) {
        this.sessionAnswers = sessionAnswers;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
