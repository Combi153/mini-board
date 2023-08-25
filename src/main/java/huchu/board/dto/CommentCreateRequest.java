package huchu.board.dto;

public record CommentCreateRequest(
        Long postId,
        String content
) {
}
