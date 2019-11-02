package quizapi.modal;


import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "questions")
public class Question {

    public Question() {
    }

    public Question(String title, Category category, String description, Collection<Answer> answers) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.answers = answers;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @OneToOne(targetEntity = Category.class, mappedBy = "category_id", optional = false)
    private Category category;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(targetEntity = Answer.class, mappedBy = "id", fetch = FetchType.EAGER)
    private Collection<Answer> answers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Collection<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Collection<Answer> answers) {
        this.answers = answers;
    }
}
