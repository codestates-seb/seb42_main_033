package Main.server.board_isfp;

import Main.server.comment.CommentDto;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import java.util.List;
import java.util.stream.Collectors;

@Primary
@Mapper(componentModel = "spring")
public class CustomBoardIsfpMapper implements BoardIsfpMapper {
    @Autowired
    private BoardIsfpMapper mapper;

    @Override
    public BoardIsfp postDtoToBoardIsfp(BoardIsfpDto.Post postDto) {
        return mapper.postDtoToBoardIsfp(postDto);
    }

    @Override
    public BoardIsfp patchDtoToBoardIsfp(BoardIsfpDto.Patch patchDto) {
        return mapper.patchDtoToBoardIsfp(patchDto);
    }

    @Override
    public BoardIsfpDto.Response boardIsfpToResponseDto(BoardIsfp post) {
        if(post == null) {
            return null;
        }
        else {
            BoardIsfpDto.Response response = new BoardIsfpDto.Response();

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
                            comment.getBoardIsfp().getId(),
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
    public List<BoardIsfpDto.Response> boardIsfpToResponseDtos(List<BoardIsfp> posts) {
        List<BoardIsfpDto.Response> responses = posts.stream()
                .map(this::boardIsfpToResponseDto)
                .collect(Collectors.toList());

        return responses;
    }
}
