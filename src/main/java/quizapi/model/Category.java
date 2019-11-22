package quizapi.model;

import javax.persistence.*;
import java.util.Collection;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(cascade = {PERSIST, REFRESH}, targetEntity = Question.class, mappedBy = "category")
    private Collection<Question> questions;

    @OneToMany(cascade = {PERSIST, REFRESH}, targetEntity = Record.class, mappedBy = "category")
    private Collection<Record> records;

    public Category() {
    }

    public Category(String name, Collection<Question> questions) {
        this.name = name;
        this.questions = questions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Collection<Question> questions) {
        this.questions = questions;
    }
}
