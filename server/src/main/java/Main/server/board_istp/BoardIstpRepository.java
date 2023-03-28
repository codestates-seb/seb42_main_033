package Main.server.board_istp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardIstpRepository extends JpaRepository<BoardIstp, Long> {
    Page<BoardIstp> findAll(Pageable pageable);
}
