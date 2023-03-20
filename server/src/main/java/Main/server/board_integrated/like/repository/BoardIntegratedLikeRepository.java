package Main.server.board_integrated.like.repository;

import Main.server.board_integrated.entity.BoardIntegrated;
import Main.server.board_integrated.like.entity.BoardIntegratedLike;
import Main.server.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardIntegratedLikeRepository extends JpaRepository<BoardIntegratedLike, Long> {
    Optional<BoardIntegratedLike> findByUsersAndPost(Users users, BoardIntegrated post);
}
