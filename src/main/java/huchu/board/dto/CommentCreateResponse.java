package huchu.board.dto;

import huchu.board.domain.Comment;

public record CommentCreateResponse(
        Long id
) {
    public static CommentCreateResponse from(Comment comment) {
        return new CommentCreateResponse(comment.id());
    }
}
