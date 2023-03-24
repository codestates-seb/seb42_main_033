package Main.server.board_integrated;

import Main.server.board_integrated.BoardIntegrated;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardIntegratedRepository extends JpaRepository<BoardIntegrated, Long> {
    Page<BoardIntegrated> findAll(Pageable pageable);
}
