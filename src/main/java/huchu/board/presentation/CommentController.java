package huchu.board.presentation;

import huchu.board.application.CommentService;
import huchu.board.dto.CommentCreateRequest;
import huchu.board.dto.CommentCreateResponse;
import huchu.board.dto.CommentUpdateRequest;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/comments")
@RestController
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CommentCreateRequest commentCreateRequest) {
        CommentCreateResponse response = commentService.create(null, commentCreateRequest);
        return ResponseEntity.created(URI.create(String.valueOf(response.id()))).build();
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<Void> update(
            @PathVariable Long commentId,
            @RequestBody CommentUpdateRequest commentUpdateRequest
    ) {
        commentService.update(null, commentId, commentUpdateRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> delete(@PathVariable Long commentId) {
        commentService.deleteById(null, commentId);
        return ResponseEntity.noContent().build();
    }
}
