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

    @ManyToOne(targetEntity = Category.class, optional = false)
    private Category category;

    @ManyToOne(targetEntity = User.class, optional = false)
    private User user;

    @ManyToMany(cascade = {CascadeType.ALL}, targetEntity = SessionAnswer.class)
    private Set<SessionAnswer> sessionAnswers;

    public GameSession() {
    }

    public GameSession(Integer score, Integer questionsCountTotal, Integer questionsCountAnswered,
                       Category category, User user, Collection<Question> questions) {
        this.score = score;
        this.questionsCountTotal = questionsCountTotal;
        this.questionsCountAnswered = questionsCountAnswered;
        this.category = category;
        this.user = user;
        this.questions = questions;
    }

    private Collection<Question> questions;

    public Collection<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Collection<Question> questions) {
        this.questions = questions;
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
}
