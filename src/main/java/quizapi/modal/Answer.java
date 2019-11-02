package quizapi.modal;

import javax.persistence.*;

@Entity
@Table(name = "answers")
public class Answer {

    public Answer() {
    }

    public Answer(Boolean isRight, String description, Question question) {
        this.isRight = isRight;
        this.description = description;
        this.question = question;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "is_right", nullable = false)
    private Boolean isRight;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToOne(targetEntity = Question.class, mappedBy = "id")
    private Question question;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getRight() {
        return isRight;
    }

    public void setRight(Boolean right) {
        isRight = right;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
