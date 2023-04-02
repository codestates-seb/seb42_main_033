package Main.server.board_estp;

import Main.server.comment.CommentDto;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import java.util.List;
import java.util.stream.Collectors;

@Primary
@Mapper(componentModel = "spring")
public class CustomBoardEstpMapper implements BoardEstpMapper {
    @Autowired
    private BoardEstpMapper mapper;

    @Override
    public BoardEstp postDtoToBoardEstp(BoardEstpDto.Post postDto) {
        return mapper.postDtoToBoardEstp(postDto);
    }

    @Override
    public BoardEstp patchDtoToBoardEstp(BoardEstpDto.Patch patchDto) {
        return mapper.patchDtoToBoardEstp(patchDto);
    }

    @Override
    public BoardEstpDto.Response boardEstpToResponseDto(BoardEstp post) {
        if(post == null) {
            return null;
        }
        else {
            BoardEstpDto.Response response = new BoardEstpDto.Response();

            response.setId(post.getId());
            response.setUserId(post.getUsers().getUserId());
            response.setNickName(post.getUsers().getNickName());
            response.setTitle(post.getTitle());
            response.setContent(post.getContent());
            response.setTag(post.getTag());
            response.setLikeCount(post.getLikeCount());
            response.setCategory(post.getUsers().getMbti());

            if(post.getCommentCount() != null) {
                response.setCommentCount(post.getCommentCount());
            }

            if(post.getViewCount() != null) {
                response.setViewCount(post.getViewCount());
            }

            if(post.getLikeCount() != null) {
                response.setLikeCount(post.getLikeCount());
            }

            response.setCreatedAt(post.getCreatedAt());
            response.setModifiedAt(post.getModifiedAt());

            List<CommentDto.Response> comments = post.getComment().stream()
                    .map(comment -> new CommentDto.Response(
                            comment.getId(),
                            comment.getUser().getUserId(),
                            comment.getBoardEstp().getId(),
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
    public List<BoardEstpDto.Response> boardEstpToResponseDtos(List<BoardEstp> posts) {
        List<BoardEstpDto.Response> responses = posts.stream()
                .map(this::boardEstpToResponseDto)
                .collect(Collectors.toList());

        return responses;
    }
}
