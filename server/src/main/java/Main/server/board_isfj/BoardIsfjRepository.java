package Main.server.board_isfj;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardIsfjRepository extends JpaRepository<BoardIsfj, Long> {
    Page<BoardIsfj> findAll(Pageable pageable);
}
