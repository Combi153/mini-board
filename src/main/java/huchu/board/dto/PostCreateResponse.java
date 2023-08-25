package huchu.board.dto;

import huchu.board.domain.Post;

public record PostCreateResponse(
        Long id
) {
    public static PostCreateResponse from(Post post) {
        return new PostCreateResponse(post.id());
    }
}
