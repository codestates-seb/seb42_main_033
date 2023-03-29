package Main.server.board_intj;

import Main.server.comment.CommentDto;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import java.util.List;
import java.util.stream.Collectors;

@Primary
@Mapper(componentModel = "spring")
public class CustomBoardIntjMapper implements BoardIntjMapper {
    @Autowired
    private BoardIntjMapper mapper;

    @Override
    public BoardIntj postDtoToBoardIntj(BoardIntjDto.Post postDto) {
        return mapper.postDtoToBoardIntj(postDto);
    }

    @Override
    public BoardIntj patchDtoToBoardIntj(BoardIntjDto.Patch patchDto) {
        return mapper.patchDtoToBoardIntj(patchDto);
    }

    @Override
    public BoardIntjDto.Response boardIntjToResponseDto(BoardIntj post) {
        if(post == null) {
            return null;
        }
        else {
            BoardIntjDto.Response response = new BoardIntjDto.Response();

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
                            comment.getBoardIntj().getId(),
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
    public List<BoardIntjDto.Response> boardIntjToResponseDtos(List<BoardIntj> posts) {
        List<BoardIntjDto.Response> responses = posts.stream()
                .map(this::boardIntjToResponseDto)
                .collect(Collectors.toList());

        return responses;
    }
}
