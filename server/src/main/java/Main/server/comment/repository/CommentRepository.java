package Main.server.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Main.server.comment.entity.Comment, Long> {
}
