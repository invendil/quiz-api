package quizapi.modal;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "users")
public class User {

    public User() {
    }

    public User(String nickname, Collection<Answer> answers, Collection<Record> records) {
        this.nickname = nickname;
        this.answers = answers;
        this.records = records;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @OneToMany(targetEntity = Answer.class, mappedBy = "id")
    private Collection<Answer> answers;

    @OneToMany(targetEntity = Record.class, mappedBy = "id")
    private Collection<Record> records;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Collection<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Collection<Answer> answers) {
        this.answers = answers;
    }

    public Collection<Record> getRecords() {
        return records;
    }

    public void setRecords(Collection<Record> records) {
        this.records = records;
    }
}
