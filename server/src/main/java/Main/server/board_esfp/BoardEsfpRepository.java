package Main.server.board_esfp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardEsfpRepository extends JpaRepository<BoardEsfp, Long> {
    Page<BoardEsfp> findAll(Pageable pageable);
}
