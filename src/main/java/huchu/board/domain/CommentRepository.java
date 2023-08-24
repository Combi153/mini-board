package huchu.board.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    default Comment getById(Long id) {
        return findById(id).orElseThrow(IllegalArgumentException::new);
    }
}
