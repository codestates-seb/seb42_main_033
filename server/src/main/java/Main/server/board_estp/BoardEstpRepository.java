package Main.server.board_estp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardEstpRepository extends JpaRepository<BoardEstp, Long> {
    Page<BoardEstp> findAll(Pageable pageable);
}
