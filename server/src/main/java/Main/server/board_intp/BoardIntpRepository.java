package Main.server.board_intp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardIntpRepository extends JpaRepository<BoardIntp, Long> {
    Page<BoardIntp> findAll(Pageable pageable);
}
