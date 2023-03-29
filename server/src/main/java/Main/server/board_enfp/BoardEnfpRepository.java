package Main.server.board_enfp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardEnfpRepository extends JpaRepository<BoardEnfp, Long> {
    Page<BoardEnfp> findAll(Pageable pageable);
}
