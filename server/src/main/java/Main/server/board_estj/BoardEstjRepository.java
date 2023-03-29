package Main.server.board_estj;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardEstjRepository extends JpaRepository<BoardEstj, Long> {
    Page<BoardEstj> findAll(Pageable pageable);
}
