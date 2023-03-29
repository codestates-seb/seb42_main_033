package Main.server.board_intj;

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
public class BoardIntjService {
    private final BoardIntjRepository boardIntjRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;

    public BoardIntjService(BoardIntjRepository boardIntjRepository,
                            UserRepository userRepository,
                            LikeRepository likeRepository,
                            CommentRepository commentRepository) {
        this.boardIntjRepository = boardIntjRepository;
        this.userRepository = userRepository;
        this.likeRepository = likeRepository;
        this.commentRepository = commentRepository;
    }

    @Transactional
    public BoardIntj createPost(BoardIntj post, long userId) {
        Users findUser = getUserFromId(userId);

        if(findUser.getMbti().equalsIgnoreCase("intj")) {
            post.setUsers(findUser);
            post.setViewCount(0L);
            post.setLikeCount(0L);
            post.setCommentCount(0L);
            post.setCategory("intj");

            return boardIntjRepository.save(post);
        }
        else {
            return null;
        }
    }

    @Transactional
    public BoardIntj updatePost(BoardIntj post) {
        BoardIntj findPost = findPost(post.getId());

        Optional.ofNullable(post.getTitle()).ifPresent(title -> findPost.setTitle(title));
        Optional.ofNullable(post.getContent()).ifPresent(content -> findPost.setContent(content));
        Optional.ofNullable(post.getTag()).ifPresent(tag -> findPost.setTag(tag));

        findPost.setModifiedAt(LocalDateTime.now());

        return findPost;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public BoardIntj readPost(long id) {
        BoardIntj post = findPost(id);
        post.setViewCount(post.getViewCount() + 1);
        return post;
    }

    @Transactional
    public Like insert(LikeDto likeDto) throws Exception {

        Users users = userRepository.findById(likeDto.getUserId())
                .orElseThrow(() -> new NotFoundException("회원 정보를 찾을 수 없습니다."));

        BoardIntj post = boardIntjRepository.findById(likeDto.getPostId())
                .orElseThrow(() -> new NotFoundException("게시글을 찾을 수 없습니다."));

        if(likeRepository.findByUsersAndBoardIntj(users, post).isPresent()) {
            throw new DuplicateResourceException("이미 추천했습니다.");
        }

        if(users.getMbti().equalsIgnoreCase("intj")) {
            Like boardIntjLike = Like.builder()
                    .boardIntj(post)
                    .users(users)
                    .category("intj")
                    .build();

            return likeRepository.save(boardIntjLike);
        }
        else {
            return null;
        }
    }

    public void delete(LikeDto likeDto) {

        Users users = userRepository.findById(likeDto.getUserId())
                .orElseThrow(() -> new NotFoundException("회원 정보를 찾을 수 없습니다."));

        BoardIntj post = boardIntjRepository.findById(likeDto.getPostId())
                .orElseThrow(() -> new NotFoundException("게시글을 찾을 수 없습니다."));

        Like boardIntjLike = likeRepository.findByUsersAndBoardIntj(users, post)
                .orElseThrow(() -> new NotFoundException("추천하지 않았습니다."));

        likeRepository.delete(boardIntjLike);
    }

    public BoardIntj addLike(long id) {
        BoardIntj post = findPost(id);
        post.setLikeCount(post.getLikeCount() + 1);
        return boardIntjRepository.save(post);
    }

    public BoardIntj deleteLike(long id) {
        BoardIntj post = findPost(id);
        post.setLikeCount(post.getLikeCount() - 1);
        return boardIntjRepository.save(post);
    }

    public void deletePost(long id) {
        BoardIntj post = findPost(id);

        List<Like> boardIntjLikes = likeRepository.findAll();

        while (post.getLikeCount() != 0) {
            for(int i = 0; i < boardIntjLikes.size(); i++) {
                Like boardIntjLike = boardIntjLikes.get(i);
                if(boardIntjLike.getCategory().equals("Intj") && boardIntjLike.getBoardIntj().getId() == id) {
                    likeRepository.delete(boardIntjLike);
                    deleteLike(id);
                }
            }
        }

        List<Comment> comments = commentRepository.findAll();

        while (post.getCommentCount() != 0) {
            for(int i = 0; i < comments.size(); i++) {
                Comment comment = comments.get(i);
                if(comment.getCategory().equals("Intj") && comment.getBoardIntj().getId() == id) {
                    commentRepository.delete(comment);
                    post.setCommentCount(post.getCommentCount() - 1);
                }
            }
        }

        boardIntjRepository.deleteById(id);
    }

    // 댓글 작성
    @Transactional
    public Comment createComment(Comment comment, long userId, long postId) {
        Users findUser = getUserFromId(userId);
        BoardIntj findPost = findPost(postId);

        if(findUser.getMbti().equalsIgnoreCase("Intj")) {
            findPost.setCommentCount(findPost.getCommentCount()+1);
            comment.setUser(findUser);
            comment.setBoardIntj(findPost);
            comment.setCategory("Intj");
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
        BoardIntj post = findComment.getBoardIntj();
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

    public BoardIntj findPost(long id) {
        return boardIntjRepository.findById(id).orElse(null);
    }

    public Page<BoardIntj> findAllPost(int page, int size) {
        return boardIntjRepository.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
    }

    public Users getUserFromId(long userId) {
        return userRepository.findById(userId).get();
    }
}
