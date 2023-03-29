package Main.server.board_esfp;

import Main.server.comment.CommentDto;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import java.util.List;
import java.util.stream.Collectors;

@Primary
@Mapper(componentModel = "spring")
public class CustomBoardEsfpMapper implements BoardEsfpMapper {
    @Autowired
    private BoardEsfpMapper mapper;

    @Override
    public BoardEsfp postDtoToBoardEsfp(BoardEsfpDto.Post postDto) {
        return mapper.postDtoToBoardEsfp(postDto);
    }

    @Override
    public BoardEsfp patchDtoToBoardEsfp(BoardEsfpDto.Patch patchDto) {
        return mapper.patchDtoToBoardEsfp(patchDto);
    }

    @Override
    public BoardEsfpDto.Response boardEsfpToResponseDto(BoardEsfp post) {
        if(post == null) {
            return null;
        }
        else {
            BoardEsfpDto.Response response = new BoardEsfpDto.Response();

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
                            comment.getBoardEsfp().getId(),
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
    public List<BoardEsfpDto.Response> boardEsfpToResponseDtos(List<BoardEsfp> posts) {
        List<BoardEsfpDto.Response> responses = posts.stream()
                .map(this::boardEsfpToResponseDto)
                .collect(Collectors.toList());

        return responses;
    }
}
