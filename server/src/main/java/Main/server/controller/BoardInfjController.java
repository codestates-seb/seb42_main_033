package Main.server.board_infj.controller;

import Main.server.board_infj.dto.BoardInfjDto;
import Main.server.board_infj.entity.BoardInfj;
import Main.server.board_infj.mapper.BoardInfjMapper;
import Main.server.board_infj.service.BoardInfjService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/board/infj")
public class BoardInfjController {
    private final BoardInfjService service;
    private final BoardInfjMapper mapper;

    public BoardInfjController(BoardInfjService service, BoardInfjMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postPost(@RequestBody BoardInfjDto.Post postDto) {
        BoardInfj createdPost = service.createPost(mapper.postDtoToBoardInfj(postDto), postDto.getUserId());
        BoardInfjDto.Response result = mapper.boardInfjToResponseDto(createdPost);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getPosts(@Positive @RequestParam(defaultValue = "1") int page,
                                   @Positive @RequestParam(defaultValue = "100") int size) {

        Page<BoardInfj> pagePosts = service.findAllPost(page - 1, size);
        List<BoardInfj> posts = pagePosts.getContent();
        List<BoardInfjDto.Response> result = mapper.boardInfjToResponseDtos(posts);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{post-id}")
    public ResponseEntity getPost(@PathVariable("post-id") long postId) {

        BoardInfj post = service.readPost(postId);
        BoardInfjDto.Response result = mapper.boardInfjToResponseDto(post);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PatchMapping("/{post-id}")
    public ResponseEntity patchPost(@PathVariable("post-id") long postId,
                                    @RequestBody BoardInfjDto.Patch patchDto) {

        BoardInfj post = mapper.patchDtoToBoardInfj(patchDto);
        post.setId(postId);

        BoardInfj modifiedPost = service.updatePost(post);
        BoardInfjDto.Response result = mapper.boardInfjToResponseDto(modifiedPost);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{post-id}")
    public ResponseEntity deletePost(@PathVariable("post-id") long postId) {

        service.deletePost(postId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
