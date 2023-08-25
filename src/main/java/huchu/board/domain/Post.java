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

    @JoinColumn(name = "member_id")
    @ManyToOne
    private Member writer;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    @Column(nullable = false)
    private boolean isDeleted = false;

    public Post() {
    }

    public Post(Long id, Member writer, String title, String content) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
    }

    public Post(Member writer, String title, String content) {
        this(null, writer, title, content);
    }

    public void validateAuthorization(Member member) {
        if (!writer.equals(member)) {
            throw new IllegalArgumentException();
        }
    }

    public void change(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
    }

    public void delete() {
        comments.forEach(Comment::delete);
        isDeleted = true;
    }

    public Long id() {
        return id;
    }

    public Member writer() {
        return writer;
    }

    public String writerNickname() {
        return writer.nickname();
    }

    public String title() {
        return title;
    }

    public String content() {
        return content;
    }

    public List<Comment> comments() {
        return new ArrayList<>(comments);
    }

    public boolean isDeleted() {
        return isDeleted;
    }
}
