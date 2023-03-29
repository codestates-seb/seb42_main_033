package Main.server.board_esfj;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardEsfjRepository extends JpaRepository<BoardEsfj, Long> {
    Page<BoardEsfj> findAll(Pageable pageable);
}
