package Main.server.board_integrated.controller;

import Main.server.board_integrated.dto.BoardIntegratedDto;
import Main.server.board_integrated.entity.BoardIntegrated;
import Main.server.board_integrated.mapper.BoardIntegratedMapper;
import Main.server.board_integrated.service.BoardIntegratedService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity getPosts() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{post-id}")
    public ResponseEntity getPost(@PathVariable("post-id") long postId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{post-id}")
    public ResponseEntity patchPost(@PathVariable("post-id") long postId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{post-id}")
    public ResponseEntity deletePost(@PathVariable("post-id") long postId) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping
    public String forTest() {
        return "This is Test Page....";
    }
}
