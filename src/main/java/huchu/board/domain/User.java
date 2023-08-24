package huchu.board.domain;

import huchu.board.domain.common.BaseEntity;

public class User extends BaseEntity {

    private Long id;
    private String nickname;

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
