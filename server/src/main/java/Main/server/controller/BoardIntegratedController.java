package Main.server.board_integrated.controller;

import Main.server.board_integrated.dto.BoardIntegratedDto;
import Main.server.board_integrated.entity.BoardIntegrated;
import Main.server.board_integrated.mapper.BoardIntegratedMapper;
import Main.server.board_integrated.service.BoardIntegratedService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/board/integrated")
public class BoardIntegratedController {
    private final BoardIntegratedService service;
    private final BoardIntegratedMapper mapper;

    public BoardIntegratedController(BoardIntegratedService service, BoardIntegratedMapper mapper) {
        this.service = service;
        this.mapper = mapper;
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


    @GetMapping("/")
    public String forTest() {
        return "This is Test Page....";
    }
}
