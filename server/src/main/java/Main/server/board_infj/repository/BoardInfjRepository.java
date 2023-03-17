package Main.server.board_infj.repository;

import Main.server.board_infj.entity.BoardInfj;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardInfjRepository extends JpaRepository<BoardInfj, Long> {
    Page<BoardInfj> findAll(Pageable pageable);
}
