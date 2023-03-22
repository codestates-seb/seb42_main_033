package Main.server.board_integrated;

import Main.server.advice.errors.DuplicateResourceException;
import Main.server.advice.errors.NotFoundException;
import Main.server.like.LikeDto;
import Main.server.like.Like;
import Main.server.like.LikeRepository;
import Main.server.user.entity.Users;
import Main.server.user.repository.UserRepository;
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

    public BoardIntegratedService(BoardIntegratedRepository boardIntegratedRepository, UserRepository userRepository, LikeRepository likeRepository) {
        this.boardIntegratedRepository = boardIntegratedRepository;
        this.userRepository = userRepository;
        this.likeRepository = likeRepository;
    }

    @Transactional
    public BoardIntegrated createPost(BoardIntegrated post, long userId) {
        Users findUser = getUserId(userId);
        post.setUsers(findUser);
        post.setViewCount(0L);
        post.setLikeCount(0L);
        post.setCommentCount(0L);
        post.setCategory("integrated");

        return boardIntegratedRepository.save(post);
    }

    @Transactional
    public BoardIntegrated updatePost(BoardIntegrated post) {
        BoardIntegrated findPost = findPost(post.getId());

        Optional.ofNullable(post.getTitle()).ifPresent(title -> findPost.setTitle(title));
        Optional.ofNullable(post.getContent()).ifPresent(content -> findPost.setContent(content));
        Optional.ofNullable(post.getTag()).ifPresent(tag -> findPost.setTag(tag));

        findPost.setModifiedAt(LocalDateTime.now());
//        return boardIntegratedRepository.save(findPost);

        return findPost;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public BoardIntegrated readPost(long id) {
        BoardIntegrated post = findPost(id);
        post.setViewCount(post.getViewCount() + 1);
        return post;
//        return boardIntegratedRepository.save(post);
    }

    @Transactional
    public Like insert(LikeDto likeDto) throws Exception {

        Users users = userRepository.findById(likeDto.getUserId())
                .orElseThrow(() -> new NotFoundException("회원 정보를 찾을 수 없습니다."));

        BoardIntegrated post = boardIntegratedRepository.findById(likeDto.getPostId())
                .orElseThrow(() -> new NotFoundException("게시글을 찾을 수 없습니다."));

        if(likeRepository.findByUsersAndPost(users, post).isPresent()) {
            throw new DuplicateResourceException("이미 추천했습니다.");
        }

        Like like = Like.builder()
                .post(post)
                .users(users)
                .category("integrated")
                .build();

        return likeRepository.save(like);
    }

    @Transactional
    public void delete(LikeDto likeDto) {

        Users users = userRepository.findById(likeDto.getUserId())
                .orElseThrow(() -> new NotFoundException("회원 정보를 찾을 수 없습니다."));

        BoardIntegrated post = boardIntegratedRepository.findById(likeDto.getPostId())
                .orElseThrow(() -> new NotFoundException("게시글을 찾을 수 없습니다."));

        Like like = likeRepository.findByUsersAndPost(users, post)
                .orElseThrow(() -> new NotFoundException("추천하지 않았습니다."));

        likeRepository.delete(like);
    }

    public BoardIntegrated addLike(long id) {
        BoardIntegrated post = findPost(id);
        post.setLikeCount(post.getLikeCount() + 1);
        return boardIntegratedRepository.save(post);
    }

    public BoardIntegrated deleteLike(long id) {
        BoardIntegrated post = findPost(id);
        post.setLikeCount(post.getLikeCount() - 1);
        return boardIntegratedRepository.save(post);
    }

    public void deletePost(long id) {
        BoardIntegrated post = findPost(id);

//        List<Like> likes = likeRepository.findByIdAndCategory(id, "integrated")
//                .stream().collect(Collectors.toList());

        List<Like> likes = likeRepository.findAll();

        while (post.getLikeCount() != 0) {
            for(int i = 0; i < likes.size(); i++) {
                Like like = likes.get(i);
                if(like.getCategory().equals("integrated") && like.getPost().getId() == id) {
                    likeRepository.delete(like);
                    deleteLike(id);
                }
            }
        }
        boardIntegratedRepository.deleteById(id);
    }

    public BoardIntegrated findPost(long id) {
        return boardIntegratedRepository.findById(id).orElse(null);
    }

//    public Like findLike(long id, String category) {
//        return likeRepository.findById(id).orElse(null);
//    }

    public Page<BoardIntegrated> findAllPost(int page, int size) {
        return boardIntegratedRepository.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
    }

    public Users getUserId(long userId) {
        return userRepository.findById(userId).get();
    }
}
