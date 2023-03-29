package Main.server.board_intp;

import Main.server.comment.CommentDto;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import java.util.List;
import java.util.stream.Collectors;

@Primary
@Mapper(componentModel = "spring")
public class CustomBoardIntpMapper implements BoardIntpMapper {
    @Autowired
    private BoardIntpMapper mapper;

    @Override
    public BoardIntp postDtoToBoardIntp(BoardIntpDto.Post postDto) {
        return mapper.postDtoToBoardIntp(postDto);
    }

    @Override
    public BoardIntp patchDtoToBoardIntp(BoardIntpDto.Patch patchDto) {
        return mapper.patchDtoToBoardIntp(patchDto);
    }

    @Override
    public BoardIntpDto.Response boardIntpToResponseDto(BoardIntp post) {
        if(post == null) {
            return null;
        }
        else {
            BoardIntpDto.Response response = new BoardIntpDto.Response();

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
                            comment.getBoardIntp().getId(),
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
    public List<BoardIntpDto.Response> boardIntpToResponseDtos(List<BoardIntp> posts) {
        List<BoardIntpDto.Response> responses = posts.stream()
                .map(this::boardIntpToResponseDto)
                .collect(Collectors.toList());

        return responses;
    }
}
