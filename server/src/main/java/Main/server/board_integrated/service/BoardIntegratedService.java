package Main.server.board_integrated.service;

import Main.server.board_integrated.entity.BoardIntegrated;
import Main.server.board_integrated.repository.BoardIntegratedRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BoardIntegratedService {
    private final BoardIntegratedRepository boardIntegratedRepository;

    public BoardIntegratedService(BoardIntegratedRepository boardIntegratedRepository) {
        this.boardIntegratedRepository = boardIntegratedRepository;
    }

    public BoardIntegrated createPost(BoardIntegrated post, long userId) {

        return boardIntegratedRepository.save(post);
    }

    public BoardIntegrated updatePost(BoardIntegrated post) {
        BoardIntegrated findPost = findPost(post.getId());

        Optional.ofNullable(post.getTitle()).ifPresent(title -> findPost.setTitle(title));
        Optional.ofNullable(post.getContent()).ifPresent(content -> findPost.setContent(content));
        Optional.ofNullable(post.getTag()).ifPresent(tag -> findPost.setTag(tag));

        findPost.setModifiedAt(LocalDateTime.now());
        return boardIntegratedRepository.save(findPost);
    }

    public BoardIntegrated readPost(long id) {
        BoardIntegrated post = findPost(id);
        post.setViewCount(post.getViewCount() + 1);
        return boardIntegratedRepository.save(post);
    }

    public void deletePost(long id) {
        boardIntegratedRepository.deleteById(id);
    }

    public BoardIntegrated findPost(long id) {
        return boardIntegratedRepository.findById(id).orElse(null);
    }

    public Page<BoardIntegrated> findAllPost(int page, int size) {
        return boardIntegratedRepository.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
    }

//    public Member getMemberId(long memberId) {
//        return memberRepository.findById(memberId).get();
//    }
}
