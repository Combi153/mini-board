package huchu.board.common;

import huchu.board.domain.Post;
import huchu.board.domain.User;

public class Fixture {

    public static User user() {
        return new User("user");
    }

    public static Post post() {
        return new Post(user(), "title", "content");
    }
}
