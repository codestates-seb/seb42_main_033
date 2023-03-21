package Main.server.like.repository;

import Main.server.board_integrated.entity.BoardIntegrated;
import Main.server.like.entity.Like;
import Main.server.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByUsersAndPost(Users users, BoardIntegrated post);
    Optional<Like> findByIdAndCategory(Long id);
}
