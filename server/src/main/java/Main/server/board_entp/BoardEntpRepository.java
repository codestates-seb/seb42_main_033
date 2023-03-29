package Main.server.board_entp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardEntpRepository extends JpaRepository<BoardEntp, Long> {
    Page<BoardEntp> findAll(Pageable pageable);
}
