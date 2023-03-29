package Main.server.comment;

import Main.server.board_integrated.BoardIntegrated;
import Main.server.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
