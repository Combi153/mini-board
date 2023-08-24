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

    @JoinColumn(name = "member_id")
    @ManyToOne
    private Member writer;

    @Column(nullable = false)
    private String content;

    @JoinColumn(name = "post_id")
    @ManyToOne
    private Post post;

    @Column(nullable = false)
    private boolean isDeleted = false;

    public Comment() {
    }

    public Comment(Long id, Member writer, String content, Post post) {
        this.id = id;
        this.writer = writer;
        this.content = content;
        this.post = post;
    }

    public Comment(Member writer, String content, Post post) {
        this(null, writer, content, post);
    }

    public void validateAuthorization(Member member) {
        if (!writer.equals(member)) {
            throw new IllegalArgumentException();
        }
    }

    public void change(String content) {
        this.content = content;
    }

    public void delete() {
        isDeleted = true;
        post.removeComment(this);
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

    public String content() {
        return content;
    }

    public Post post() {
        return post;
    }

    public boolean isDeleted() {
        return isDeleted;
    }
}
