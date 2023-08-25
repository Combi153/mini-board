package huchu.board.dto;

import huchu.board.domain.Comment;

public record CommentResponse(
        Long id,
        String writerNickname,
        String content
) {
    public static CommentResponse from(Comment comment) {
        return new CommentResponse(comment.id(), comment.writerNickname(), comment.content());
    }
}
