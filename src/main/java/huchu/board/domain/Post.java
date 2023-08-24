package huchu.board.domain;

import static jakarta.persistence.GenerationType.IDENTITY;

import huchu.board.domain.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Post extends BaseEntity {

    @GeneratedValue(strategy = IDENTITY)
    @Id
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User writer;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    public Post() {
    }

    public Post(Long id, User writer, String title, String content) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
    }

    public Post(User writer, String title, String content) {
        this(null, writer, title, content);
    }

    public void validateAuthorization(User user) {
        if (!writer.equals(user)) {
            throw new IllegalArgumentException();
        }
    }

    public void change(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Long id() {
        return id;
    }

    public User writer() {
        return writer;
    }

    public String title() {
        return title;
    }

    public String content() {
        return content;
    }

    public List<Comment> comments() {
        return comments;
    }
}
