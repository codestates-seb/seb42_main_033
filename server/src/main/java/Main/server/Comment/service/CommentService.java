package Main.server.Comment.service;

import Main.server.Comment.entity.Comment;
import Main.server.board_integrated.entity.BoardIntegrated;
import Main.server.board_integrated.repository.BoardIntegratedRepository;
import Main.server.user.entity.Users;
import Main.server.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private final Main.server.Comment.repository.CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BoardIntegratedRepository boardIntegratedRepository;

    public CommentService(Main.server.Comment.repository.CommentRepository commentRepository, UserRepository userRepository, BoardIntegratedRepository boardIntegratedRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.boardIntegratedRepository = boardIntegratedRepository;
    }

    public Comment createComment(Comment comment, long userId, long BoardId) {
        Users findUser = getUserFromId(userId);
        BoardIntegrated findBoard = getBoardIntegratedFromId(BoardId);
        findBoard.setCommentCount(findBoard.getCommentCount()+1);
        comment.setUser(findUser);
        comment.setBoard(findBoard);
        return commentRepository.save(comment);
    }

    public Comment updateComment(Comment comment) {
        Comment findComment = findVerifiedComment(comment.getCommentId());
        Optional.ofNullable(comment.getContent()).ifPresent(content -> findComment.setContent(content));

        findComment.setModifiedAt(LocalDateTime.now());
        return commentRepository.save(findComment);
    }

    public void deleteComment(long commentId) {
        Comment findComment = findVerifiedComment(commentId);
        BoardIntegrated board = findComment.getBoard();
        board.setCommentCount(board.getCommentCount()-1);
        commentRepository.deleteById(commentId);

        //answerRepository.delete(findAnswer);
    }

    public Comment findVerifiedComment(long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public List<Comment> findComments() {
        return commentRepository.findAll();
    }

    public Users getUserFromId(Long userId) {
        return userRepository.findById(userId).get();
    }

    public BoardIntegrated getBoardIntegratedFromId(Long boardId) {
        return boardIntegratedRepository.findById(boardId).get();
    }

}
