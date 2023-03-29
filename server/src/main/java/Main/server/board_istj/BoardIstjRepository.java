package Main.server.board_istj;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardIstjRepository extends JpaRepository<BoardIstj, Long> {
    Page<BoardIstj> findAll(Pageable pageable);
}
