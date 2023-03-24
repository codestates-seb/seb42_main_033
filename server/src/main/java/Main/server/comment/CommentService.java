//package Main.server.comment;
//
//import Main.server.board_integrated.BoardIntegrated;
//import Main.server.board_integrated.BoardIntegratedRepository;
//import Main.server.user.entity.Users;
//import Main.server.user.repository.UserRepository;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class CommentService {
//    private final CommentRepository commentRepository;
//    private final UserRepository userRepository;
//    private final BoardIntegratedRepository boardIntegratedRepository;
//
//    public CommentService(CommentRepository commentRepository, UserRepository userRepository, BoardIntegratedRepository boardIntegratedRepository) {
//        this.commentRepository = commentRepository;
//        this.userRepository = userRepository;
//        this.boardIntegratedRepository = boardIntegratedRepository;
//    }
//
//    @Transactional
//    public Comment createComment(Comment comment, long userId, long postId) {
//        Users findUser = getUserFromId(userId);
//        BoardIntegrated findPost = getBoardIntegratedFromId(postId);
//        findPost.setCommentCount(findPost.getCommentCount()+1);
//        comment.setUser(findUser);
//        comment.setPost(findPost);
//        comment.setCategory("integrated");
//        return commentRepository.save(comment);
//    }
//
//    @Transactional
//    public Comment updateComment(Comment comment) {
//        Comment findComment = findVerifiedComment(comment.getId());
//        Optional.ofNullable(comment.getContent()).ifPresent(content -> findComment.setContent(content));
//
//        findComment.setModifiedAt(LocalDateTime.now());
//        return commentRepository.save(findComment);
//    }
//
//    @Transactional
//    public void deleteComment(long commentId) {
//        Comment findComment = findVerifiedComment(commentId);
//        BoardIntegrated post = findComment.getPost();
//        commentRepository.deleteById(commentId);
//        post.setCommentCount(post.getCommentCount()-1);
//        //answerRepository.delete(findAnswer);
//    }
//
//    public Comment findVerifiedComment(long commentId) {
//        return commentRepository.findById(commentId).orElse(null);
//    }
//
//    public List<Comment> findComments() {
//        return commentRepository.findAll();
//    }
//
//    public Users getUserFromId(Long userId) {
//        return userRepository.findById(userId).get();
//    }
//
//    public BoardIntegrated getBoardIntegratedFromId(Long postId) {
//        return boardIntegratedRepository.findById(postId).get();
//    }
//
//}
