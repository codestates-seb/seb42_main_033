package Main.server.board_infj;

import Main.server.comment.CommentDto;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import java.util.List;
import java.util.stream.Collectors;

@Primary
@Mapper(componentModel = "spring")
public class CustomBoardInfjMapper implements BoardInfjMapper {
    @Autowired
    private BoardInfjMapper mapper;

    @Override
    public BoardInfj postDtoToBoardInfj(BoardInfjDto.Post postDto) {
        return mapper.postDtoToBoardInfj(postDto);
    }

    @Override
    public BoardInfj patchDtoToBoardInfj(BoardInfjDto.Patch patchDto) {
        return mapper.patchDtoToBoardInfj(patchDto);
    }

    @Override
    public BoardInfjDto.Response boardInfjToResponseDto(BoardInfj post) {
        if(post == null) {
            return null;
        }
        else {
            BoardInfjDto.Response response = new BoardInfjDto.Response();

            response.setId(post.getId());
            response.setUserId(post.getUsers().getUserId());
            response.setNickName(post.getUsers().getNickName());
            response.setTitle(post.getTitle());
            response.setContent(post.getContent());
            response.setTag(post.getTag());

            if(post.getCommentCount() != null) {
                response.setCommentCount(post.getCommentCount());
            }

            if(post.getViewCount() != null) {
                response.setViewCount(post.getViewCount());
            }

            response.setCreatedAt(post.getCreatedAt());
            response.setModifiedAt(post.getModifiedAt());

            List<CommentDto.Response> comments = post.getComment().stream()
                    .map(comment -> new CommentDto.Response(
                            comment.getId(),
                            comment.getUser().getUserId(),
                            comment.getBoardInfj().getId(),
                            comment.getContent(),
                            comment.getUser().getNickName(),
                            comment.getCategory()
                    ))
                    .collect(Collectors.toList());

            response.setComments(comments);

            return response;
        }
    }

    @Override
    public List<BoardInfjDto.Response> boardInfjToResponseDtos(List<BoardInfj> posts) {
        List<BoardInfjDto.Response> responses = posts.stream()
                .map(this::boardInfjToResponseDto)
                .collect(Collectors.toList());

        return responses;
    }
}
