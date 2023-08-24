package huchu.board.domain;

import static jakarta.persistence.GenerationType.IDENTITY;

import huchu.board.domain.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class User extends BaseEntity {

    @GeneratedValue(strategy = IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String nickname;

    public User() {
    }

    public User(Long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }

    public User(String nickname) {
        this(null, nickname);
    }

    public Long id() {
        return id;
    }

    public String nickname() {
        return nickname;
    }
}
