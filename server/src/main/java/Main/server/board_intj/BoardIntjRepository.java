package Main.server.board_intj;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardIntjRepository extends JpaRepository<BoardIntj, Long> {
    Page<BoardIntj> findAll(Pageable pageable);
}
