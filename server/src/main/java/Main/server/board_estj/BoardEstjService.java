package Main.server.board_estj;

import Main.server.advice.errors.DuplicateResourceException;
import Main.server.advice.errors.NotFoundException;
import Main.server.comment.Comment;
import Main.server.comment.CommentRepository;
import Main.server.like.Like;
import Main.server.like.LikeDto;
import Main.server.like.LikeRepository;
import Main.server.user.UserRepository;
import Main.server.user.Users;
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
public class BoardEstjService {
    private final BoardEstjRepository boardEstjRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;

    public BoardEstjService(BoardEstjRepository boardEstjRepository,
                            UserRepository userRepository,
                            LikeRepository likeRepository,
                            CommentRepository commentRepository) {
        this.boardEstjRepository = boardEstjRepository;
        this.userRepository = userRepository;
        this.likeRepository = likeRepository;
        this.commentRepository = commentRepository;
    }

    @Transactional
    public BoardEstj createPost(BoardEstj post, long userId) {
        Users findUser = getUserFromId(userId);

        if(findUser.getMbti().equalsIgnoreCase("estj")) {
            post.setUsers(findUser);
            post.setViewCount(0L);
            post.setLikeCount(0L);
            post.setCommentCount(0L);
            post.setCategory("estj");

            return boardEstjRepository.save(post);
        }
        else {
            return null;
        }
    }

    @Transactional
    public BoardEstj updatePost(BoardEstj post) {
        BoardEstj findPost = findPost(post.getId());

        Optional.ofNullable(post.getTitle()).ifPresent(title -> findPost.setTitle(title));
        Optional.ofNullable(post.getContent()).ifPresent(content -> findPost.setContent(content));
        Optional.ofNullable(post.getTag()).ifPresent(tag -> findPost.setTag(tag));

        findPost.setModifiedAt(LocalDateTime.now());

        return findPost;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public BoardEstj readPost(long id) {
        BoardEstj post = findPost(id);
        post.setViewCount(post.getViewCount() + 1);
        return post;
    }

    @Transactional
    public Like insert(LikeDto likeDto) throws Exception {

        Users users = userRepository.findById(likeDto.getUserId())
                .orElseThrow(() -> new NotFoundException("회원 정보를 찾을 수 없습니다."));

        BoardEstj post = boardEstjRepository.findById(likeDto.getPostId())
                .orElseThrow(() -> new NotFoundException("게시글을 찾을 수 없습니다."));

        if(likeRepository.findByUsersAndBoardEstj(users, post).isPresent()) {
            throw new DuplicateResourceException("이미 추천했습니다.");
        }

        if(users.getMbti().equalsIgnoreCase("estj")) {
            Like boardEstjLike = Like.builder()
                    .boardEstj(post)
                    .users(users)
                    .category("estj")
                    .build();

            return likeRepository.save(boardEstjLike);
        }
        else {
            return null;
        }
    }

    public void delete(LikeDto likeDto) {

        Users users = userRepository.findById(likeDto.getUserId())
                .orElseThrow(() -> new NotFoundException("회원 정보를 찾을 수 없습니다."));

        BoardEstj post = boardEstjRepository.findById(likeDto.getPostId())
                .orElseThrow(() -> new NotFoundException("게시글을 찾을 수 없습니다."));

        Like boardEstjLike = likeRepository.findByUsersAndBoardEstj(users, post)
                .orElseThrow(() -> new NotFoundException("추천하지 않았습니다."));

        likeRepository.delete(boardEstjLike);
    }

    public BoardEstj addLike(long id) {
        BoardEstj post = findPost(id);
        post.setLikeCount(post.getLikeCount() + 1);
        return boardEstjRepository.save(post);
    }

    public BoardEstj deleteLike(long id) {
        BoardEstj post = findPost(id);
        post.setLikeCount(post.getLikeCount() - 1);
        return boardEstjRepository.save(post);
    }

    public void deletePost(long id) {
        BoardEstj post = findPost(id);

        List<Like> boardEstjLikes = likeRepository.findAll();

        while (post.getLikeCount() != 0) {
            for(int i = 0; i < boardEstjLikes.size(); i++) {
                Like boardEstjLike = boardEstjLikes.get(i);
                if(boardEstjLike.getCategory().equals("estj") && boardEstjLike.getBoardEstj().getId() == id) {
                    likeRepository.delete(boardEstjLike);
                    deleteLike(id);
                }
            }
        }

        List<Comment> comments = commentRepository.findAll();

        while (post.getCommentCount() != 0) {
            for(int i = 0; i < comments.size(); i++) {
                Comment comment = comments.get(i);
                if(comment.getCategory().equals("estj") && comment.getBoardEstj().getId() == id) {
                    commentRepository.delete(comment);
                    post.setCommentCount(post.getCommentCount() - 1);
                }
            }
        }

        boardEstjRepository.deleteById(id);
    }

    // 댓글 작성
    @Transactional
    public Comment createComment(Comment comment, long userId, long postId) {
        Users findUser = getUserFromId(userId);
        BoardEstj findPost = findPost(postId);

        if(findUser.getMbti().equalsIgnoreCase("estj")) {
            findPost.setCommentCount(findPost.getCommentCount()+1);
            comment.setUser(findUser);
            comment.setBoardEstj(findPost);
            comment.setCategory("estj");
            return commentRepository.save(comment);
        }
        else {
            return null;
        }
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
        BoardEstj post = findComment.getBoardEstj();
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

    public BoardEstj findPost(long id) {
        return boardEstjRepository.findById(id).orElse(null);
    }

    public Page<BoardEstj> findAllPost(int page, int size) {
        return boardEstjRepository.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
    }

    public Users getUserFromId(long userId) {
        return userRepository.findById(userId).get();
    }
}
