package huchu.board.common;

import huchu.board.domain.Comment;
import huchu.board.domain.Member;
import huchu.board.domain.Post;

public class Fixture {

    public static Member user() {
        return new Member("user");
    }

    public static Post post() {
        return new Post(user(), "title", "content");
    }

    public static Comment comment() {
        return new Comment(user(), "content", post());
    }
}
