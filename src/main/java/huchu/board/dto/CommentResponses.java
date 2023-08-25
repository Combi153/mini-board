package huchu.board.dto;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import huchu.board.domain.Comment;
import java.util.List;

public record CommentResponses(
        List<CommentResponse> commentResponses
) {
    public static CommentResponses from(List<Comment> comments) {
        return comments.stream()
                .map(CommentResponse::from)
                .collect(collectingAndThen(toList(), CommentResponses::new));
    }
}
