package huchu.board.application;

import huchu.board.domain.CommentRepository;
import huchu.board.domain.Member;
import huchu.board.domain.MemberRepository;
import huchu.board.domain.Post;
import huchu.board.domain.PostRepository;
import huchu.board.dto.LoginUser;
import huchu.board.dto.PostCreateRequest;
import huchu.board.dto.PostCreateResponse;
import huchu.board.dto.PostResponse;
import huchu.board.dto.PostUpdateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;

    public PostService(
            PostRepository postRepository,
            MemberRepository memberRepository,
            CommentRepository commentRepository
    ) {
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
        this.commentRepository = commentRepository;
    }

    public PostCreateResponse create(LoginUser loginUser, PostCreateRequest postCreateRequest) {
        Member member = memberRepository.getById(loginUser.memberId());
        Post post = postRepository.save(new Post(member, postCreateRequest.title(), postCreateRequest.content()));
        return PostCreateResponse.from(post);
    }

    @Transactional(readOnly = true)
    public PostResponse readById(Long postId) {
        Post post = postRepository.getById(postId);
        return PostResponse.from(post);
    }

    public void update(LoginUser loginUser, Long postId, PostUpdateRequest postUpdateRequest) {
        Post post = authorizeAndGetPost(loginUser, postId);
        post.change(postUpdateRequest.title(), postUpdateRequest.content());
    }

    private Post authorizeAndGetPost(LoginUser loginUser, Long postId) {
        Member member = memberRepository.getById(loginUser.memberId());
        Post post = postRepository.getById(postId);
        post.validateAuthorization(member);
        return post;
    }

    public void deleteById(LoginUser loginUser, Long postId) {
        Post post = authorizeAndGetPost(loginUser, postId);
        post.delete();
    }
}
