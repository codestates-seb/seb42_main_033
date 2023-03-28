package Main.server.board_infp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardInfpRepository extends JpaRepository<BoardInfp, Long> {
    Page<BoardInfp> findAll(Pageable pageable);
}
