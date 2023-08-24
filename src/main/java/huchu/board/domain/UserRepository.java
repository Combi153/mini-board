package huchu.board.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    default User getById(Long id) {
        return findById(id).orElseThrow(IllegalArgumentException::new);
    }
}
