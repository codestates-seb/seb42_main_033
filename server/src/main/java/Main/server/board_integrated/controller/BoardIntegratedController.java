package Main.server.board_integrated.controller;

import Main.server.board_integrated.dto.BoardIntegratedDto;
import Main.server.board_integrated.entity.BoardIntegrated;
import Main.server.board_integrated.mapper.BoardIntegratedMapper;
import Main.server.board_integrated.mapper.CustomBoardIntegratedMapper;
import Main.server.board_integrated.service.BoardIntegratedService;
import Main.server.like.dto.LikeDto;
import Main.server.like.entity.Like;
import Main.server.like.mapper.LikeMapper;
import Main.server.like.repository.LikeRepository;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/board/integrated")
public class BoardIntegratedController {
    private final BoardIntegratedService service;
    private final CustomBoardIntegratedMapper mapper;
    private final LikeMapper likeMapper;
    private final LikeRepository likeRepository;

    public BoardIntegratedController(BoardIntegratedService service, CustomBoardIntegratedMapper mapper, LikeMapper likeMapper, LikeRepository likeRepository) {
        this.service = service;
        this.mapper = mapper;
        this.likeMapper = likeMapper;
        this.likeRepository = likeRepository;
    }

    @PostMapping
    public ResponseEntity postPost(@RequestBody BoardIntegratedDto.Post postDto) {
        BoardIntegrated createdPost = service.createPost(mapper.postDtoToBoardIntegrated(postDto), postDto.getUserId());
        BoardIntegratedDto.Response result = mapper.boardIntegratedToResponseDto(createdPost);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getPosts(@Positive @RequestParam(defaultValue = "1") int page,
                                   @Positive @RequestParam(defaultValue = "100") int size) {

        Page<BoardIntegrated> pagePosts = service.findAllPost(page - 1, size);
        List<BoardIntegrated> posts = pagePosts.getContent();
        List<BoardIntegratedDto.Response> result = mapper.boardIntegratedToResponseDtos(posts);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{post-id}")
    public ResponseEntity getPost(@PathVariable("post-id") long postId) {
        BoardIntegrated post = service.readPost(postId);
        BoardIntegratedDto.Response result = mapper.boardIntegratedToResponseDto(post);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PatchMapping("/{post-id}")
    public ResponseEntity patchPost(@PathVariable("post-id") long postId,
                                    @RequestBody BoardIntegratedDto.Patch patchDto) {
        BoardIntegrated post = mapper.patchDtoToBoardIntegrated(patchDto);
        post.setId(postId);

        BoardIntegrated modifiedPost = service.updatePost(post);
        BoardIntegratedDto.Response result = mapper.boardIntegratedToResponseDto(modifiedPost);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{post-id}")
    public ResponseEntity deletePost(@PathVariable("post-id") long postId) {

        service.deletePost(postId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //추천 관련
    @PostMapping("/{post-id}/like")
    public ResponseEntity insert(@PathVariable("post-id") long postId,
                                 @RequestBody LikeDto likeDto) throws Exception {

        if(postId == likeDto.getPostId()) {
            service.insert(likeDto);
            service.addLike(postId);

//            Like like = likeMapper.likeDtoToLike(likeDto);

            return new ResponseEntity(HttpStatus.OK);
        }
        else {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{post-id}/like")
    public ResponseEntity delete(@PathVariable("post-id") long postId,
                                 @RequestBody @Valid LikeDto likeDto) {

        if(postId == likeDto.getPostId()) {

            service.delete(likeDto);
            service.deleteLike(postId);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public String forTest() {
        return "This is Test Page....";
    }
}
