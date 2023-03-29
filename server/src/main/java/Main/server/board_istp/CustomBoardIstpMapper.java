package Main.server.board_istp;

import Main.server.comment.CommentDto;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import java.util.List;
import java.util.stream.Collectors;

@Primary
@Mapper(componentModel = "spring")
public class CustomBoardIstpMapper implements BoardIstpMapper {
    @Autowired
    private BoardIstpMapper mapper;

    @Override
    public BoardIstp postDtoToBoardIstp(BoardIstpDto.Post postDto) {
        return mapper.postDtoToBoardIstp(postDto);
    }

    @Override
    public BoardIstp patchDtoToBoardIstp(BoardIstpDto.Patch patchDto) {
        return mapper.patchDtoToBoardIstp(patchDto);
    }

    @Override
    public BoardIstpDto.Response boardIstpToResponseDto(BoardIstp post) {
        if(post == null) {
            return null;
        }
        else {
            BoardIstpDto.Response response = new BoardIstpDto.Response();

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
                            comment.getBoardIstp().getId(),
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
    public List<BoardIstpDto.Response> boardIstpToResponseDtos(List<BoardIstp> posts) {
        List<BoardIstpDto.Response> responses = posts.stream()
                .map(this::boardIstpToResponseDto)
                .collect(Collectors.toList());

        return responses;
    }
}
