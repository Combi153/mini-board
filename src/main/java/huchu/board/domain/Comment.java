package huchu.board.domain;

import static jakarta.persistence.GenerationType.IDENTITY;

import huchu.board.domain.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Comment extends BaseEntity {

    @GeneratedValue(strategy = IDENTITY)
    @Id
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User writer;

    @Column(nullable = false)
    private String content;

    @JoinColumn(name = "post_id")
    @ManyToOne
    private Post post;

    public Comment() {
    }

    public Comment(Long id, User writer, String content, Post post) {
        this.id = id;
        this.writer = writer;
        this.content = content;
        this.post = post;
    }

    public Comment(User writer, String content, Post post) {
        this(null, writer, content, post);
    }

    public void validateAuthorization(User user) {
        if (!writer.equals(user)) {
            throw new IllegalArgumentException();
        }
    }

    public void change(String content) {
        this.content = content;
    }

    public Long id() {
        return id;
    }

    public User writer() {
        return writer;
    }

    public String content() {
        return content;
    }

    public Post post() {
        return post;
    }
}
