package Main.server.board_entj;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardEntjRepository extends JpaRepository<BoardEntj, Long> {
    Page<BoardEntj> findAll(Pageable pageable);
}
