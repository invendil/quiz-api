package quizapi.model;


import javax.persistence.*;
import java.util.Collection;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @ManyToOne(targetEntity = Category.class, optional = false)
    private Category category;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "difficulty", nullable = false)
    private String difficulty;

    @OneToMany(cascade = ALL, targetEntity = Answer.class, mappedBy = "id")
    private Collection<Answer> answers;

    public Question() {
    }

    public Question(Category category, String description, String difficulty, Collection<Answer> answers) {
        this.category = category;
        this.description = description;
        this.difficulty = difficulty;
        this.answers = answers;
    }

    public Long getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Collection<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Collection<Answer> answers) {
        this.answers = answers;
    }
}
