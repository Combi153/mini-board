package huchu.board.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    default Member getById(Long id) {
        return findById(id).orElseThrow(IllegalArgumentException::new);
    }
}
