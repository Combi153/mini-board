package huchu.board.dto;

import huchu.board.domain.Post;

public record PostResponse(
        Long id,
        String writerNickname,
        String title,
        String content,
        CommentResponses commentResponses
) {
    public static PostResponse from(Post post) {
        return new PostResponse(
                post.id(),
                post.writerNickname(),
                post.title(),
                post.content(),
                CommentResponses.from(post.comments())
        );
    }
}
