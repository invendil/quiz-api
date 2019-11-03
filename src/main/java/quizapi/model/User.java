package quizapi.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "users")
public class User {

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public User(String username, Collection<Answer> answers, Collection<Record> records) {
        this.username = username;
        this.answers = answers;
        this.records = records;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
