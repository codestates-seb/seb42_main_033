package Main.server.board_integrated;

import Main.server.advice.errors.DuplicateResourceException;
import Main.server.advice.errors.NotFoundException;
import Main.server.comment.Comment;
import Main.server.comment.CommentRepository;
import Main.server.like.Like;
import Main.server.like.LikeDto;
import Main.server.like.LikeRepository;
import Main.server.user.Users;
import Main.server.user.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BoardIntegratedService {
    private final BoardIntegratedRepository boardIntegratedRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;

    public BoardIntegratedService(BoardIntegratedRepository boardIntegratedRepository,
                                  UserRepository userRepository,
                                  LikeRepository likeRepository,
                                  CommentRepository commentRepository) {
        this.boardIntegratedRepository = boardIntegratedRepository;
        this.userRepository = userRepository;
        this.likeRepository = likeRepository;
        this.commentRepository = commentRepository;
    }

    // 게시글 작성
    @Transactional
    public BoardIntegrated createPost(BoardIntegrated post, long userId) {
        Users findUser = getUserFromId(userId);
        post.setUsers(findUser);
        post.setViewCount(0L);
        post.setLikeCount(0L);
        post.setCommentCount(0L);
        post.setCategory("integrated");

        return boardIntegratedRepository.save(post);
    }

    // 게시글 수정
    @Transactional
    public BoardIntegrated updatePost(BoardIntegrated post) {
        BoardIntegrated findPost = findPost(post.getId());

        Optional.ofNullable(post.getTitle()).ifPresent(title -> findPost.setTitle(title));
        Optional.ofNullable(post.getContent()).ifPresent(content -> findPost.setContent(content));
        Optional.ofNullable(post.getTag()).ifPresent(tag -> findPost.setTag(tag));

        findPost.setModifiedAt(LocalDateTime.now());

        return findPost;
    }

    // 게시글 조회 시 조회수 +1
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public BoardIntegrated readPost(long id) {
        BoardIntegrated post = findPost(id);
        post.setViewCount(post.getViewCount() + 1);
        return post;
    }

    // 게시글 추천
    @Transactional
    public Like insert(LikeDto likeDto) throws Exception {

        Users users = userRepository.findById(likeDto.getUserId())
                .orElseThrow(() -> new NotFoundException("회원 정보를 찾을 수 없습니다."));

        BoardIntegrated post = boardIntegratedRepository.findById(likeDto.getPostId())
                .orElseThrow(() -> new NotFoundException("게시글을 찾을 수 없습니다."));

        if(likeRepository.findByUsersAndBoardIntegrated(users, post).isPresent()) {
            throw new DuplicateResourceException("이미 추천했습니다.");
        }

        Like boardIntegratedLike = Like.builder()
                .boardIntegrated(post)
                .users(users)
                .category("integrated")
                .build();

        return likeRepository.save(boardIntegratedLike);
    }

    // 게시글 추천 취소
    @Transactional
    public void delete(LikeDto likeDto) {

        Users users = userRepository.findById(likeDto.getUserId())
                .orElseThrow(() -> new NotFoundException("회원 정보를 찾을 수 없습니다."));

        BoardIntegrated post = boardIntegratedRepository.findById(likeDto.getPostId())
                .orElseThrow(() -> new NotFoundException("게시글을 찾을 수 없습니다."));

        Like boardIntegratedLike = likeRepository.findByUsersAndBoardIntegrated(users, post)
                .orElseThrow(() -> new NotFoundException("추천하지 않았습니다."));

        likeRepository.delete(boardIntegratedLike);
    }

    // 추천수 +1 -> insert
    public BoardIntegrated addLike(long id) {
        BoardIntegrated post = findPost(id);
        post.setLikeCount(post.getLikeCount() + 1);
        return boardIntegratedRepository.save(post);
    }

    // 추천수 -1 -> delete
    public BoardIntegrated deleteLike(long id) {
        BoardIntegrated post = findPost(id);
        post.setLikeCount(post.getLikeCount() - 1);
        return boardIntegratedRepository.save(post);
    }

    // 게시글 삭제 -> 삭제 시 해당 게시글에 달린 추천, 댓글을 먼저 삭제
    public void deletePost(long id) {
        BoardIntegrated post = findPost(id);

        List<Like> boardIntegratedLikes = likeRepository.findAll();

        while (post.getLikeCount() != 0) {
            for(int i = 0; i < boardIntegratedLikes.size(); i++) {
                Like boardIntegratedLike = boardIntegratedLikes.get(i);
                if(boardIntegratedLike.getCategory().equals("integrated") && boardIntegratedLike.getBoardIntegrated().getId() == id) {
                    likeRepository.delete(boardIntegratedLike);
                    deleteLike(id);
                }
            }
        }

        List<Comment> comments = commentRepository.findAll();

        while (post.getCommentCount() != 0) {
            for(int i = 0; i < comments.size(); i++) {
                Comment comment = comments.get(i);
                if(comment.getCategory().equals("integrated") && comment.getBoardIntegrated().getId() == id) {
                    commentRepository.delete(comment);
                    post.setCommentCount(post.getCommentCount()-1);
                }
            }
        }
        boardIntegratedRepository.deleteById(id);
    }

    // 댓글 작성
    @Transactional
    public Comment createComment(Comment comment, long userId, long postId) {
        Users findUser = getUserFromId(userId);
        BoardIntegrated findPost = findPost(postId);
        findPost.setCommentCount(findPost.getCommentCount()+1);
        comment.setUser(findUser);
        comment.setBoardIntegrated(findPost);
        comment.setCategory("integrated");
        return commentRepository.save(comment);
    }

    // 댓글 수정
    @Transactional
    public Comment updateComment(Comment comment) {
        Comment findComment = findVerifiedComment(comment.getId());
        Optional.ofNullable(comment.getContent()).ifPresent(content -> findComment.setContent(content));

        findComment.setModifiedAt(LocalDateTime.now());
        return commentRepository.save(findComment);
    }

    // 댓글 삭제
    @Transactional
    public void deleteComment(long commentId) {
        Comment findComment = findVerifiedComment(commentId);
        BoardIntegrated post = findComment.getBoardIntegrated();
        commentRepository.deleteById(commentId);
        post.setCommentCount(post.getCommentCount()-1);
    }

    // 댓글 조회
    public Comment findVerifiedComment(long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    // 전체 댓글 조회
    public List<Comment> findComments() {
        return commentRepository.findAll();
    }

    // 사용자 조회
    public Users getUserFromId(Long userId) {
        return userRepository.findById(userId).get();
    }

    // 게시글 조회
    public BoardIntegrated findPost(long id) {
        return boardIntegratedRepository.findById(id).orElse(null);
    }

    // 전체 게시글 조회
    public Page<BoardIntegrated> findAllPost(int page, int size) {
        return boardIntegratedRepository.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
    }
}
