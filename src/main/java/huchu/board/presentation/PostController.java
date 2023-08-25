package huchu.board.presentation;

import huchu.board.application.PostService;
import huchu.board.dto.PostCreateRequest;
import huchu.board.dto.PostCreateResponse;
import huchu.board.dto.PostResponse;
import huchu.board.dto.PostUpdateRequest;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/posts")
@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody PostCreateRequest postCreateRequest) {
        PostCreateResponse response = postService.create(null, postCreateRequest);
        return ResponseEntity.created(URI.create(String.valueOf(response.id()))).build();
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> read(@PathVariable Long postId) {
        PostResponse response = postService.readById(postId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Void> update(@PathVariable Long postId, @RequestBody PostUpdateRequest postUpdateRequest) {
        postService.update(null, postId, postUpdateRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> delete(@PathVariable Long postId) {
        postService.deleteById(null, postId);
        return ResponseEntity.noContent().build();
    }
}
