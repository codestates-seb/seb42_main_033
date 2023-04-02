package Main.server.board_esfj;

import Main.server.comment.CommentDto;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import java.util.List;
import java.util.stream.Collectors;

@Primary
@Mapper(componentModel = "spring")
public class CustomBoardEsfjMapper implements BoardEsfjMapper {
    @Autowired
    private BoardEsfjMapper mapper;

    @Override
    public BoardEsfj postDtoToBoardEsfj(BoardEsfjDto.Post postDto) {
        return mapper.postDtoToBoardEsfj(postDto);
    }

    @Override
    public BoardEsfj patchDtoToBoardEsfj(BoardEsfjDto.Patch patchDto) {
        return mapper.patchDtoToBoardEsfj(patchDto);
    }

    @Override
    public BoardEsfjDto.Response boardEsfjToResponseDto(BoardEsfj post) {
        if(post == null) {
            return null;
        }
        else {
            BoardEsfjDto.Response response = new BoardEsfjDto.Response();

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
                            comment.getBoardEsfj().getId(),
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
    public List<BoardEsfjDto.Response> boardEsfjToResponseDtos(List<BoardEsfj> posts) {
        List<BoardEsfjDto.Response> responses = posts.stream()
                .map(this::boardEsfjToResponseDto)
                .collect(Collectors.toList());

        return responses;
    }
}
