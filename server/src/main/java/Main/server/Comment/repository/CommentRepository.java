package Main.server.Comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Main.server.Comment.entity.Comment, Long> {
}
