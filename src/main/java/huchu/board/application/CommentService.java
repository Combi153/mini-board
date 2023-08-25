package huchu.board.application;

import huchu.board.domain.Comment;
import huchu.board.domain.CommentRepository;
import huchu.board.domain.Member;
import huchu.board.domain.MemberRepository;
import huchu.board.domain.Post;
import huchu.board.domain.PostRepository;
import huchu.board.dto.CommentCreateRequest;
import huchu.board.dto.CommentCreateResponse;
import huchu.board.dto.CommentUpdateRequest;
import huchu.board.dto.LoginUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    public CommentService(
            CommentRepository commentRepository,
            MemberRepository memberRepository,
            PostRepository postRepository
    ) {
        this.commentRepository = commentRepository;
        this.memberRepository = memberRepository;
        this.postRepository = postRepository;
    }

    public CommentCreateResponse create(LoginUser loginUser, CommentCreateRequest commentCreateRequest) {
        Member member = memberRepository.getById(loginUser.memberId());
        Post post = postRepository.getById(commentCreateRequest.postId());
        Comment comment = commentRepository.save(new Comment(member, commentCreateRequest.content(), post));
        return CommentCreateResponse.from(comment);
    }

    public void update(
            LoginUser loginUser,
            Long commentId,
            CommentUpdateRequest commentUpdateRequest
    ) {
        Comment comment = authroizeAndGetComment(loginUser, commentId);
        comment.change(commentUpdateRequest.content());
    }

    private Comment authroizeAndGetComment(LoginUser loginUser, Long commentId) {
        Member member = memberRepository.getById(loginUser.memberId());
        Comment comment = commentRepository.getById(commentId);
        comment.validateAuthorization(member);
        return comment;
    }

    public void deleteById(LoginUser loginUser, Long commentId) {
        Comment comment = authroizeAndGetComment(loginUser, commentId);
        comment.delete();
    }
}
