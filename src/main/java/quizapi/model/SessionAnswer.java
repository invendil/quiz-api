package quizapi.model;

import javax.persistence.*;

@Entity
@Table(name = "session_answers")
public class SessionAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "game_session_id", nullable = false)
    private GameSession gameSession;

    @ManyToOne(targetEntity = Answer.class)
    private Answer answer;

    public SessionAnswer() {
    }

    public SessionAnswer(GameSession gameSession, Answer answer) {
        this.gameSession = gameSession;
        this.answer = answer;
    }

    public Long getId() {
        return id;
    }

    public GameSession getGameSession() {
        return gameSession;
    }

    public void setGameSession(GameSession gameSession) {
        this.gameSession = gameSession;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
}
